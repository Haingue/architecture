package com.imt.intes.partservice.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/css/**",
                        "/js/**").permitAll()
            .regexMatchers("/swagger-ui.*", "/api-docs.*").permitAll()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers(HttpMethod.GET, "/service/**").permitAll()
            .antMatchers("/service/**").authenticated()
            .antMatchers("/hello").permitAll()
            .antMatchers(HttpMethod.GET, "/client/**").permitAll()
            .antMatchers("/client/**").authenticated()
            .anyRequest().denyAll()
            .and()
                .formLogin().permitAll()
            .and()
                .logout().permitAll();

        http.csrf().disable();

        http.cors().disable();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}
