package com.example.desafio_spring_boot.service.impl;

import org.springframework.stereotype.Service;

import com.example.desafio_spring_boot.entity.User;
import com.example.desafio_spring_boot.exceptions.AuthOperationException;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.model.response.auth.TokenResponseDto;
import com.example.desafio_spring_boot.repository.UserRepository;
import com.example.desafio_spring_boot.service.AuthService;
import com.example.desafio_spring_boot.utils.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ApiResponseDto<?> authenticateAndGenerateToken(String username, String password) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AuthOperationException("Usuario no encontrado en base de datos"));

        if (password.equals(user.getPassword())) {
            String token = jwtUtils.getToken(user);
            return ApiResponseDto.builder()
                    .message("Operacion exitosa")
                    .statusCode(200)
                    .data(TokenResponseDto.builder().token(token).build())
                    .build();
        }

        throw new AuthOperationException("Error al generar token");
    }

}
