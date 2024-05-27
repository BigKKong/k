package com.yourpackage;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XMLBlockingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("XMLBlockingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String uri = httpRequest.getRequestURI();
        System.out.println("Request URI: " + uri);
        
        if (uri.endsWith(".xml")) {
            System.out.println("Blocking access to: " + uri);
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access to XML files is forbidden");
        } else {
            chain.doFilter(request, response); // Prosegui con la catena di filtri
        }
    }

    @Override
    public void destroy() {
        System.out.println("XMLBlockingFilter destroyed");
    }
}
