package com.example.desafio_spring_boot.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto<T> {

    private String message;
    private int statusCode;
    private T data;
    
}
