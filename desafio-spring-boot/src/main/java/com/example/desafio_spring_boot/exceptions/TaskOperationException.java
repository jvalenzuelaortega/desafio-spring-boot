package com.example.desafio_spring_boot.exceptions;

public class TaskOperationException extends RuntimeException {

    public TaskOperationException(String message) {
        super(message);
    }

    public TaskOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
