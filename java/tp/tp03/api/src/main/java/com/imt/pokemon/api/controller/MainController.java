package com.imt.pokemon.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    private final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Value("${springdoc.swagger-ui.path:/swagger-ui/index.html}")
    private String swaggerPath;

    @GetMapping
    public RedirectView redirectToSwagger (RedirectAttributes attributes) {
        return new RedirectView(swaggerPath);
    }

}
