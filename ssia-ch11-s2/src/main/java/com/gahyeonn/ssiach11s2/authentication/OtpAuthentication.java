package com.gahyeonn.ssiach11s2.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

//OTP를 암호 취급하기 때문에 UsernamePasswordAuthenticationToken 이용함
public class OtpAuthentication extends UsernamePasswordAuthenticationToken {
    public OtpAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
