package com.imt.services.joboffer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private SecurityProperties securityProperties;

    /** ONLY FOR TESTING: use a simple method to load/store one user in memory **/
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username(securityProperties.getUser().getName())
                .password(securityProperties.getUser().getPassword())
                .roles("user", "admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /** Configure SpringSecurity filter chain **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/api-docs/**","/swagger-ui/**").permitAll()
                    .requestMatchers("/mcp/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/", "/hello", "/hello/**", "/h2-console**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/service/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/service/**").hasRole("admin")
                    .requestMatchers("/service/**").hasRole("user")
                    .anyRequest().authenticated()
                )
                .httpBasic(httpBasicCustomizer->httpBasicCustomizer.init(http))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    /** Configure CORS policy **/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:*")); // Allow only one source
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all header
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Use this configuration for all endpoints
        return source;
    }
}
