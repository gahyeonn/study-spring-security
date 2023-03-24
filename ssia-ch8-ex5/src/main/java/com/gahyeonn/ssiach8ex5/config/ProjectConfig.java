package com.gahyeonn.ssiach8ex5.config;

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
                .authorities("read")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .authorities("read", "premium")
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

        http.authorizeRequests()
                //사용 추천 : 권한 부여 규칙을 위해 경로를 해석하는 방법과 스프링이 경로를 엔드포인트에 매핑하기 위해 해석하는 방법이 같기 때문
//                .mvcMatchers("/hello").authenticated(); // '/hello', '/hello/' 둘 다 적용 => 사용 추천
                .antMatchers("/hello").authenticated()
                .regexMatchers(".*/(us|uk|ca)+/(en|fr).*").authenticated()
                .anyRequest().hasAuthority("premium");// '/hello'에만 적용 => 모든 경우에 대한 확실한 식 작성 필요
    }
}
