package com.gahyeonn.ssiach5ex1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 WebSecurityConfigurerAdapter의 configure()메서드 재정의를 통한 AuthenticationProvider의 새 구현 연결
 */
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    //스프링은 AuthenticationProvider가 추상 인터페이스임을 인식하고 컨텍스트에서 해당 특정 인터페이스의 구현 인스턴스를 찾아야 함 => CustomAuthenticationProvider
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
