package com.gahyeonn.ssiach2ex2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * configure 메서드를 통해 UserDetailsService와 PasswordEncoder 설정
 */
//@Configuration => 적용시 해제하기
public class ProjectConfig2 extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //InMemoryUserDetailsManager의 인스턴스 생성
        var userDetailsService = new InMemoryUserDetailsManager();

        //사용자 생성
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        //UserDetailsService에서 관리하도록 사용자 추가
        userDetailsService.createUser(user);

        //UserDetailsService 및 PasswordEncoder 구성
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .anyRequest().authenticated(); //모든 요청에 인증을 요구하도록 지정
    }
}
