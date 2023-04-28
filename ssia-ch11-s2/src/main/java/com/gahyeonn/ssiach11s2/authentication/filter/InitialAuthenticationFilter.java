package com.gahyeonn.ssiach11s2.authentication.filter;

import com.gahyeonn.ssiach11s2.authentication.OtpAuthentication;
import com.gahyeonn.ssiach11s2.authentication.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InitialAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login"); //login 경로일 때만 해당 필터 적용
    }

    //요청에 따라 올바른 인증을 요구하도록 함
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");

        if (code == null) {
            Authentication a = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(a);
        } else { //Otp 인증 수행
            Authentication a = new OtpAuthentication(username, code);
            manager.authenticate(a);

            SecretKey key = Keys.hmacShaKeyFor(
                    signingKey.getBytes(StandardCharsets.UTF_8));

            //jwt를 구축하고 인증된 사용자의 사용자 이름을 클레임 중 하나로 저장. 토큰을 서명하는데 이용함
            String jwt = Jwts.builder()
                    .setClaims(Map.of("username", username)) //jwt 본문에 값 추가
                    .signWith(key) //토큰에 서명 첨부
                    .compact();

            //생성된 토큰을 http 응답의 권한 부여 헤더에 추가
            response.setHeader("Authorization", jwt);
        }
    }
}
