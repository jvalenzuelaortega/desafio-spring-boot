package com.example.desafio_spring_boot.patterns.cor;

import com.example.desafio_spring_boot.exceptions.ValidationFieldException;
import com.example.desafio_spring_boot.model.request.TaskRequestDto;

public class DescriptionValidator extends BaseValidator<TaskRequestDto> {

    @Override
    public void validate(TaskRequestDto request) throws ValidationFieldException {
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            throw new ValidationFieldException("La descripcion es requerida");
        }
        super.validate(request);
    }

}
