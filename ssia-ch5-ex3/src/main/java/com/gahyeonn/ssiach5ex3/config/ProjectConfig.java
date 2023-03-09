package com.gahyeonn.ssiach5ex3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //인증 방식과 관련한 일부 구성(영역 이름)을 설정할 수 있음
        //영역(realm)은 특정 인증 방식을 이용하는 보호 공간
        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });

        http.authorizeRequests().anyRequest().authenticated();

    }
}
