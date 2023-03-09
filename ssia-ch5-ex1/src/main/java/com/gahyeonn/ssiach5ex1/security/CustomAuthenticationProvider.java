package com.gahyeonn.ssiach5ex1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        //UserDetails를 가져오기 위함
        //사용자가 존재하지 않으면 loadUserByUsername()는 AuthenticationException을 투척 => 인증 프로세스 중단, HTTP 필터 응답 상태 401 권한 없음
        UserDetails u = userDetailsService.loadUserByUsername(username);

        //사용자 암호 확인
        if (passwordEncoder.matches(password, u.getPassword())) {
            //UsernamePasswordAuthenticationToken()은 Authentication 인터페이스의 한 구현이며, 사용자 이름과 암호를 이용하는 표준 인증 요청
            return new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
        } else {
            //암호가 일치하지 않으면 AuthenticationException 형식의 예외 발생
            //BadCredentialsException은 AuthenticationException을 상속
            throw new BadCredentialsException("Something went wrong!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
