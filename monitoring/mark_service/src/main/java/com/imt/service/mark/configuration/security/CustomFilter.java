package com.imt.service.mark.configuration.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            String uri = request.getRequestURI();
            if (isValidUri(uri)) {
                String username = request.getHeader("Authorization");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e);
        }
 
        filterChain.doFilter(request, response);
        throw new UnsupportedOperationException("Unimplemented method 'doFilter'");
    }

    private boolean isValidUri (String uri) {
        /* A remplacer par une réglèe plus complexe */
        return true;
    }
}
