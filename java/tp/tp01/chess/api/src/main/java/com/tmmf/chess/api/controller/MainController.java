package com.tmmf.chess.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    @Value("${springdoc.api-docs.path}")
    private String apiDocsPath;

    @GetMapping
    public RedirectView redirect() {
        return new RedirectView(apiDocsPath);
    }

}
