package com.example.desafio_spring_boot.config.exception.security;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponseDto<?> apiResponseDto = ApiResponseDto.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message("No tienes autorizacion para este acceder a este recurso")
                .build();

        String jsonResponse = objectMapper.writeValueAsString(apiResponseDto);

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }

}
