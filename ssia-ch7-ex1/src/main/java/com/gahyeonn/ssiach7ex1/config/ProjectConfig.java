package com.gahyeonn.ssiach7ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAnyAuthority;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                .password("12345")
                .authorities("READ")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .authorities("WRITE")
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

        http.authorizeRequests() //엔드포인트에 대한 권한 부여 규칙 지정
                .anyRequest() // 이용된 URL이나 HTTP 방식과 관계없이 모든 요청에 대해 규칙을 적용
//                .permitAll() //인증 여부와 관계없이 모든 요청에 대해 접근을 허용
//                .hasAuthority("WRITE"); //사용자 권한 제한 첫번째 방법: 해당하는 권한이 있는 사용자만 엔드포인트 호출
                .hasAnyAuthority("WRITE", "READ"); //사용자 권한 제한 두번째 방법: 주어진 권한 중 하나라도 있으면 요청 수행
    }
}
