package com.example.desafio_spring_boot.config.exception.security;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponseDto<?> apiResponseDto = ApiResponseDto.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message("Tienes prohibido acceder a este recurso")
                .build();

        String jsonResponse = objectMapper.writeValueAsString(apiResponseDto);

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }

}
