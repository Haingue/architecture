package com.imt.service.mark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public String getHome () {
        return "Service up !";
    }

}
