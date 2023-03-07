package com.gahyeonn.ssiach2ex2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 권한 부여 관리를 위한 구성 클래스 정의
 */
@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .anyRequest().authenticated(); // 모든 요청에 인증 필요
//                .anyRequest().permitAll(); // 모든 요청 인증 필요 X
    }
}
