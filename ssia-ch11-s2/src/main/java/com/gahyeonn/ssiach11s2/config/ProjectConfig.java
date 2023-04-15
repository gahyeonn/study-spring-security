package com.gahyeonn.ssiach11s2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfig {

    //proxy 클래스에 대한 restTemplate 빈 정의
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
