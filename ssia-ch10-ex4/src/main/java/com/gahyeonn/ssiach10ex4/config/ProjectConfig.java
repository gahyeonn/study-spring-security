package com.gahyeonn.ssiach10ex4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cors()를 호출해 cors 구성을 정의 -> 허용되는 출처와 메서드를 설정하는 CorsConfiguration 객체 생성
        http.cors(c -> { //Customizer<CorsConfigurer> 객체를 매개 변수로 받음
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(
                        List.of("example.com", "example.org")
                );
                config.setAllowedMethods( //메서드도 함께 지정 필요 -> 출처만 지정하면 요청 허용 안됨 => 기본적으로 아무 메서드도 정의하지 않기 때문
                        List.of("GET", "POST", "PUT", "DELETE")
                );
                return config;
            };
            c.configurationSource(source);
        });

        http.csrf().disable();

        http.authorizeRequests()
                .anyRequest().permitAll();
    }
}
