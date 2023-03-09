package com.gahyeonn.ssiach5ex2.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync
public class ProjectConfig {

    //비동기 사용 시 메서드가 보안 컨텍스트를 상속하지 않는 다른 스레드에서 실행되기 때문에 기존 MODE_THREADLOCAL 전략으로는 보안 컨텍스트 사용 X
    //InitializingBean을 이용해서 SecurityContextHolder 모드 설정
    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(
                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
        );
    }
}
