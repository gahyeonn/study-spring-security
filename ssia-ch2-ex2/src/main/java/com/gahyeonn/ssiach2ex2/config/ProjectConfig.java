package com.gahyeonn.ssiach2ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 스프링 컨텍스트에 빈으로 추가해 UserDetailsService와 PasswordEncoder를 정의하는 방법
 *
 * 자동 구성된 기본 구성 요소 대신 컨텍스트에 추가한 UserDetailsService형식의 인스턴스 이용
 * 사용자, PasswordEncoder 정의 필요
 * 콘솔에 자동 생성된 암호 출력되지 않음
 */

//WebSecurityConfigurerAdapter 클래스 확장 : 보안이 필요한 엔드포인트에 다른 권한 부여 규칙 선택 가능, configure() 재정의 가능
@Configuration //클래스를 구성 클래스로 표시
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    //UserDetailsService 빈에 대한 구성 클래스
    @Bean //반환된 값을 스프링 컨텍스트에 빈으로 추가하도록 스프링에 지시
    public UserDetailsService userDetailsService() {

        //InMemoryUserDetailsManager : 메모리에 자격 증명을 저장해서 스프링 시큐리티가 요청을 인증할 때 이용
        var userDetailsService = new InMemoryUserDetailsManager();

        //User 빌더 클래스로 UserDetailsService에 필요한 사용자 생성
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        userDetailsService.createUser(user); //UserDetailsService에서 관리하도록 사용자 추가

        return userDetailsService;
    }

    //PasswordEncoder 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        //NoOpPasswordEncoder : 암호를 일반 텍스트로 처리
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
//                .anyRequest().authenticated(); // 모든 요청에 인증 필요
                .anyRequest().permitAll(); // 모든 요청 인증 필요 X
    }
}
