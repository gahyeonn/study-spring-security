package com.gahyeonn.ssiach9ex3.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/*
    OncePerRequestFilter
    필터가 두번 이상 호출되지 않게 보장
    * HTTP 요청만 지원 => 형식을 형 변환하여 HttpServletRequest 및 HttpServletResponse로 직접 요청을 수신
        -> filter 인터페이스의 경우에는 요청과 응답을 형 변환해야 함
    * 필터가 적용될지 결정하는 논리 구현 가능 : 필터 체인에 추가한 필터가 특정 요청에는 적용되지 않는다고 결정 가능
        => shouldNotFilter(HttpServletResquest) 메서드 재정의
    * 기본적으로 비동기 요청이나 오류 발송 요청에는 적용 X
        => shouldNotFilterAsyncDispatch() 및 shouldNotFilterErrorDispatch() 재정의
 */
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader("Request-id");

        logger.info("Successfully authenticated request with id " + requestId);

        filterChain.doFilter(request, response);
    }
}
