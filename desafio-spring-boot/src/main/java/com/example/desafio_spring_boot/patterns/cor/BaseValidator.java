package com.example.desafio_spring_boot.patterns.cor;

import com.example.desafio_spring_boot.exceptions.ValidationFieldException;

public abstract class BaseValidator<T> implements Validator<T> {

    private Validator<T> next;

    @Override
    public void setNext(Validator<T> validator) {
        this.next = validator;
    }

    public void validate(T request) throws ValidationFieldException {
        if (next != null) {
            next.validate(request);
        }
    }

}
