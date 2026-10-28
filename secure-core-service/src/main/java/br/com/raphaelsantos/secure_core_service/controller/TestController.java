package br.com.raphaelsantos.secure_core_service.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping ("/api")
public class TestController {

    @GetMapping ("/secure")
    public String secure(Authentication authentication) {
        return "Authenticated user: " + authentication.getName();
    }
}