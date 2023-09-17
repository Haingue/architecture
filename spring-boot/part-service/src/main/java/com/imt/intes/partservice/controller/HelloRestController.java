package com.imt.intes.partservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorldMessage () {
        return ResponseEntity.ok("Hello world !");
    }
}
