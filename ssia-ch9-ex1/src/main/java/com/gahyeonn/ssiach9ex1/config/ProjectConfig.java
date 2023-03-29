package com.gahyeonn.ssiach9ex1.config;

import com.gahyeonn.ssiach9ex1.filter.RequestValidationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore( //필터 체인에서 인증 필터 앞에 맞춤형 필터의 인스턴스를 추가
                new RequestValidationFilter(), //필터 체인에 추가할 맞춤형 필터의 인스턴스
                BasicAuthenticationFilter.class) //새 인스턴스를 추가할 위치(기존 필터)
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
