package com.gahyeonn.ssiach10ex1.filter;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * _csrf 요청 특성에서 CSRF 토큰의 값을 얻고 콘솔에 출력
 */
public class CsrfTokenLogger implements Filter {

    private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Object o = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;

        logger.info("CSRF token " + token.getToken());

        chain.doFilter(request, response);
     }
}
