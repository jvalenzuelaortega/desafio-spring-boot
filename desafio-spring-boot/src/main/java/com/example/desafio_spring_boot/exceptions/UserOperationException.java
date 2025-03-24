package com.example.desafio_spring_boot.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOperationException extends RuntimeException {

    public UserOperationException(String message) {
        super(message);
    }

    public UserOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
