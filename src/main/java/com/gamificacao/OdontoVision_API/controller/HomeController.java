package com.gamificacao.OdontoVision_API.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // GET /
    @GetMapping("/")
    public ResponseEntity<Void> redirectToSwagger() {
        return ResponseEntity
                .status(302)
                .header("Location", "/swagger-ui/index.html")
                .build();
    }
}
