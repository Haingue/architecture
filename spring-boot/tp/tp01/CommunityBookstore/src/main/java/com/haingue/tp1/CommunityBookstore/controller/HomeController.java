package com.haingue.tp1.CommunityBookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "views/index";
    }

    @GetMapping("/ui")
    public String home() {
        return "views/home";
    }

    @GetMapping("/ui/login")
    public String login() {
        return "views/login";
    }

}
