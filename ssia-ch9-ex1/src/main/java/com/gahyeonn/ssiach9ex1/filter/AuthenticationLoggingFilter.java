package com.gahyeonn.ssiach9ex1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter implements Filter {

    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;

        var requestId = httpRequest.getHeader("Request-id");

        logger.info("Successfully authenticated request whit id " + requestId);

        filterChain.doFilter(request, response); //요청을 필터 체인의 다음 필터에 전달
    }
}
