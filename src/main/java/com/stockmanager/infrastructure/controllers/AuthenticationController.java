package com.stockmanager.infrastructure.controllers;

import com.stockmanager.config.security.JwtService;
import com.stockmanager.config.security.dto.JwtResponse;
import com.stockmanager.config.security.dto.LoginRequestDto;
import com.stockmanager.domain.user.UserService;
import com.stockmanager.domain.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    private final JwtService jwtService;
    private final UserService userService;

    public AuthenticationController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> authenticateAndCreateToken(@RequestBody LoginRequestDto loginRequestDto) {
        JwtResponse jwtResponse = jwtService.createAuthenticationToken(loginRequestDto);
        return ResponseEntity.ok().body(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto userResponseDto = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

    //authorization testing method
    @GetMapping("/secured")
    public ResponseEntity<String> secured() {
        return ResponseEntity.ok().body("secured");
    }
}
