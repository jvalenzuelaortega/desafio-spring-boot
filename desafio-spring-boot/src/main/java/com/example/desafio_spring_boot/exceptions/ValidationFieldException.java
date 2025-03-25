package com.example.desafio_spring_boot.exceptions;

public class ValidationFieldException extends RuntimeException {

    public ValidationFieldException(String message) {
        super(message);
    }
}
