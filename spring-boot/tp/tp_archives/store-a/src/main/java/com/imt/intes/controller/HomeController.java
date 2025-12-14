package com.imt.intes.controller;

import com.imt.intes.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private String port;
    @Autowired
    private Environment environment;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String getHomePage (Authentication authentication, Model model) {
        logger.info("Load the home page");
        model.addAttribute("port", port);
        if (authentication != null) model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("environmentName", Arrays.toString(environment.getActiveProfiles()));
        model.addAttribute("items", itemService.findAllItemEntity());
        return "home";
    }

}
