package com.gahyeonn.ssiach11s2.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

//처음 Authentication 객체를 구축할 때는 매개변수가 2개인 생성자를 이용하며 아직 인증되지 않은 상태
//AuthenticationProvider 객체가 요청을 인증할 땐느 매개 변수가 3개인 생성자로 Authentication 인스턴스를 만들며 인증된 객체가 됨
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
    //인증 인스턴스가 인증되지 않은 상태로 유지
    //=> AuthenticationManager는 요청을 인증할 올바른 AuthenticationProvider 객체를 찾음
    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    //authentication 객체가 인증됨 => 프로세스 완료
    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
