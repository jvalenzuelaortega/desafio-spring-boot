package com.example.desafio_spring_boot.service;

import com.example.desafio_spring_boot.model.response.ApiResponseDto;

public interface AuthService {

    ApiResponseDto<?> authenticateAndGenerateToken(String username, String password);
}
