package com.imt.intes.controller;

import com.imt.intes.service.weather.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public String getWeatherPage (@PathVariable String city, Model model) {
        logger.info("Load weather page for city: {}", city);
        model.addAttribute("city", city);
        model.addAttribute("weather", weatherService.getWeatherForOneCity(city));
        return "weather";
    }

}
