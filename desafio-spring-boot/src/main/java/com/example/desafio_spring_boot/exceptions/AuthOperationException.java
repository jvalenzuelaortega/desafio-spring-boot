package com.example.desafio_spring_boot.exceptions;

public class AuthOperationException extends RuntimeException {

    public AuthOperationException(String message) {
        super(message);
    }

}
