package com.imt.intes.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /*
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Optional<UserEntity> user = userService.findOneByLogin(userPrincipal.getUsername());

        if (user.isPresent()) {
            String token = jwtUtils.generateJwtToken(user.get());
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(token);
        }
         */
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.generateJwtToken(((UserDetails) authentication.getPrincipal()).getUsername());
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(token);
        }
    }
}
