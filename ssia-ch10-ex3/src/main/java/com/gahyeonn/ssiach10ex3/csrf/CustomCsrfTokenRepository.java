package com.gahyeonn.ssiach10ex3.csrf;

import com.gahyeonn.ssiach10ex3.entity.Token;
import com.gahyeonn.ssiach10ex3.repository.JpaTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    @Autowired
    private JpaTokenRepository jpaTokenRepository;

    // 새 토큰 생성
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    //특정 클라이언트를 위해 생성된 토큰 저장
    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {

        String identifier = request.getHeader("X-IDENTIFIER");

        // 클라이언트 ID로 데이터베이스에서 토큰을 얻음
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) { //ID가 존재하면 새로 생성된 값으로 토큰의 값을 업데이트
            Token token = new Token();
            token.setToken(csrfToken.getToken());
        } else { //ID가 존재하지 않으면 생성된 CSRF 토큰의 값과 ID로 새 레코드 생성
            Token token = new Token();
            token.setToken(csrfToken.getToken());
            token.setIdentifier(identifier);
            jpaTokenRepository.save(token);
        }
    }

    //토큰 세부 정보가 있으면 이를 로드하고 없으면 null 반환
    @Override
    public CsrfToken loadToken(HttpServletRequest request) {

        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_CSRF", token.getToken());
        }

        return null;
    }
}
