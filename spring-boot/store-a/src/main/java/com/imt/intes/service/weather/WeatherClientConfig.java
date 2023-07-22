package com.imt.intes.service.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WeatherClientConfig {

    @Value("${weather.token}")
    private String token;

    @Value("${weather.baseurl}")
    private String baseUrl;

    @Bean
    public WebClient weatherClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, token)
                .build();
    }
}
