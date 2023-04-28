package com.gahyeonn.ssiach11s2.config;

import com.gahyeonn.ssiach11s2.authentication.filter.InitialAuthenticationFilter;
import com.gahyeonn.ssiach11s2.authentication.filter.JwtAuthenticationFilter;
import com.gahyeonn.ssiach11s2.authentication.provider.OtpAuthenticationProvider;
import com.gahyeonn.ssiach11s2.authentication.provider.UsernamePasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final InitialAuthenticationFilter initialAuthenticationFilter;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.addFilterAt(
                        initialAuthenticationFilter,
                        BasicAuthenticationFilter.class)
                .addFilterAfter(
                        jwtAuthenticationFilter,
                        BasicAuthenticationFilter.class
                );

        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}
