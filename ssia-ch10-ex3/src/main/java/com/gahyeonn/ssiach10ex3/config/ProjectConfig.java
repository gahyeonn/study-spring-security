package com.gahyeonn.ssiach10ex3.config;

import com.gahyeonn.ssiach10ex3.csrf.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public CsrfTokenRepository customTokenRepository() {
        return new CustomCsrfTokenRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(
                c-> {
                    c.csrfTokenRepository(customTokenRepository());
                    //csrf 보호 메커니즘에서 제외할 경로를 나타내는 앤트식 지정
                    //RequestMatcher는 더 범용적 => 일반 MVC 식, 정규식으로 제외 규칙 적용 가능
                    c.ignoringAntMatchers("/ciao");
                });

        http.authorizeRequests()
                .anyRequest().permitAll();
    }
}
