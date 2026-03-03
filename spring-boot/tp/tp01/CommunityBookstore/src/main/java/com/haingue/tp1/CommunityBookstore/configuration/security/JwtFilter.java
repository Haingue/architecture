package com.haingue.tp1.CommunityBookstore.configuration.security;

import com.haingue.tp1.CommunityBookstore.model.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String tokenStr = String.valueOf(request.getHeader(HEADER_NAME));
        if (tokenStr == null || !tokenStr.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        tokenStr = tokenStr.replace(TOKEN_PREFIX, "");
        if (jwtService.validateToken(tokenStr)) {
            String extractUsername = jwtService.extractUsername(tokenStr);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                extractUsername,
                null,
                jwtService.extractRoles(tokenStr)
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            throw new UserPrincipalNotFoundException("Invalid token");
        }
        filterChain.doFilter(request, response);
    }

}
