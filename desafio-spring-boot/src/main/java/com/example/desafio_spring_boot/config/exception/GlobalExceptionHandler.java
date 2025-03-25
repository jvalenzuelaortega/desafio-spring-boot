package com.example.desafio_spring_boot.config.exception;

import com.example.desafio_spring_boot.exceptions.ValidationFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.desafio_spring_boot.exceptions.AuthOperationException;
import com.example.desafio_spring_boot.exceptions.TaskOperationException;
import com.example.desafio_spring_boot.exceptions.UserOperationException;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserOperationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponseDto<Object>> handleUserOperationException(UserOperationException exception) {
        return ResponseEntity.ok(ApiResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .data(exception.getStackTrace()[0])
                .build());
    }

    @ExceptionHandler({AuthOperationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponseDto<Object>> handleAuthOperationException(AuthOperationException exception) {
        return ResponseEntity.ok(ApiResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .data(exception.getStackTrace()[0])
                .build());
    }

    @ExceptionHandler({TaskOperationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponseDto<Object>> handleTaskOperationException(TaskOperationException exception) {
        return ResponseEntity.ok(ApiResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .data(exception.getStackTrace()[0])
                .build());
    }

    @ExceptionHandler({ValidationFieldException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponseDto<Object>> handleValidationFieldException(ValidationFieldException exception) {
        return ResponseEntity.ok(ApiResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .data(exception.getStackTrace()[0])
                .build());
    }

}
