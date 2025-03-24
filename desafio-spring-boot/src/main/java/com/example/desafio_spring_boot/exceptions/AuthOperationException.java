package com.example.desafio_spring_boot.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthOperationException extends RuntimeException {

    public AuthOperationException(String message) {
        super(message);
    }

    public AuthOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
