package com.stockmanager.infrastructure.controllers;

import com.stockmanager.config.security.JwtService;
import com.stockmanager.config.security.dto.JwtResponse;
import com.stockmanager.config.security.dto.LoginRequestDto;
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
    public ResponseEntity<JwtResponse> authenticateAndCreateToken(@RequestBody LoginRequestDto loginRequestDto) {
        JwtResponse jwtResponse = jwtService.createAuthentication(loginRequestDto);
        return ResponseEntity.ok().body(jwtResponse);
    }

}
