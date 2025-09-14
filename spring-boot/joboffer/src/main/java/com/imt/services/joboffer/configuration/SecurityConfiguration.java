package com.imt.services.joboffer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user", "admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/api-docs/**","/swagger-ui/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/", "/hello", "/h2-console**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/service/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/service/**").hasRole("admin")
                    .requestMatchers("/service/**").hasRole("user")
                    .anyRequest().authenticated()
                )
                .httpBasic(httpBasicCustomizer->httpBasicCustomizer.init(http))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/swagger-ui/**", "/api-docs*/**");
//    }
}
