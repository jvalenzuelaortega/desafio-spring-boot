package com.example.desafio_spring_boot.patterns.cor;

import com.example.desafio_spring_boot.exceptions.ValidationFieldException;
import com.example.desafio_spring_boot.model.request.TaskRequestDto;

public class UsernameValidator extends BaseValidator<TaskRequestDto> {

    @Override
    public void validate(TaskRequestDto taskRequestDto) {
        if (taskRequestDto.getUsername() == null || taskRequestDto.getUsername().isEmpty()) {
            throw new ValidationFieldException("El nombre de usuario es requerido");
        }
        super.validate(taskRequestDto);
    }
}
