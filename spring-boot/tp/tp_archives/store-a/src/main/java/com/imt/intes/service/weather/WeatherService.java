package com.imt.intes.service.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Map;

@Service
public class WeatherService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("weatherClient")
    private WebClient client;

    public String getWeatherForOneCity (String city) {
        Map cityWeather = client.get()
                .uri("/current/"+city)
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorReturn(Map.of("weather", "not found"))
                .block(Duration.ofMinutes(1));
        logger.debug("Weather API result: {}", cityWeather);
        return cityWeather.get("weather").toString();
    }
}
