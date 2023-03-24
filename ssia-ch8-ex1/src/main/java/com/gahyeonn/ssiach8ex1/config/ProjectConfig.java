package com.gahyeonn.ssiach8ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        //선택기로 요청을 참조할 때는 특정한 규칙부터 일반적인 규칙의 순서로 지정
        http.authorizeRequests()
                .mvcMatchers("/hello").hasRole("ADMIN") //경로에 mvc 식을 이용해 엔드포인트 선택
                .mvcMatchers("/ciao").hasRole("MANAGER")
                .anyRequest().permitAll(); //위 두 엔드포인트 제외 모든 엔드포인트에 대한 권한 설정 => 없어도 되지만 명시적으로 작성하는게 좋음
    }
}
