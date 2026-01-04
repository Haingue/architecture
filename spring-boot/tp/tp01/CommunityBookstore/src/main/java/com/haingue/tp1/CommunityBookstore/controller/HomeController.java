package com.haingue.tp1.CommunityBookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/ui/accessDenied")
    public RedirectView accessDenied(RedirectAttributes attributes) {
        attributes.addAttribute("errorMessage", "Access denied");
        return new RedirectView("/ui/login");
    }

}
