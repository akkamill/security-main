package org.pro.security.configs;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestId = httpRequest.getHeader("Request-Id");

        if (requestId == null || requestId=="") { httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST); return;
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }


}
