package com.example.desafio_spring_boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/get-token")
    public ResponseEntity<ApiResponseDto<?>> generateToken(@RequestParam String username, @RequestParam String password) {
        ApiResponseDto<?> responseDto = authService.authenticateAndGenerateToken(username, password);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
