package org.pro.security.configs;

//public class AuthenticationLoggingFilter implements Filter {
//    private final Logger logger =
//            Logger.getLogger(AuthenticationLoggingFilter.class.getName());
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestId = httpRequest.getHeader("Request-Id");
//        logger.info("Successfully authenticated request with id " + requestId);
//        chain.doFilter(request, response);
//    }
//
//}


import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private final Logger logger =
            Logger.getLogger(
                    AuthenticationLoggingFilter.class.getName());
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws
            ServletException, IOException {
        String requestId = request.getHeader("Request-Id");
        logger.info("Successfully authenticated request with id " +
                requestId);
        filterChain.doFilter(request, response);
    }
}