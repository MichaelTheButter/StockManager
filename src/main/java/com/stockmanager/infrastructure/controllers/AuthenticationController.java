package com.stockmanager.infrastructure.controllers;

import com.stockmanager.config.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    private final JwtService jwtService;

    public AuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtService.JWTResponse> authenticateAndCreateToken(@RequestBody JwtService.LoginRequestDto loginRequestDto) {
        JwtService.JWTResponse jwtResponse = jwtService.createAuthentication(loginRequestDto);
        return ResponseEntity.ok().body(jwtResponse);
    }

}
