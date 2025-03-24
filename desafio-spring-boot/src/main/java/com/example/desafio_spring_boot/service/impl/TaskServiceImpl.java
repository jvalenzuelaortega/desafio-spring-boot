package com.example.desafio_spring_boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.desafio_spring_boot.entity.Task;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.repository.TaskRepository;
import com.example.desafio_spring_boot.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ApiResponseDto<?> getAllTask() {
        List<Task> getTasksList = taskRepository.findAll();
        if (getTasksList.isEmpty()) {
            return ApiResponseDto.builder()
                    .statusCode(404)
                    .message("No hay datos de listas")
                    .data(null)
                    .build();
        }

        return ApiResponseDto.builder()
                .statusCode(200)
                .message("Operacion exitosa")
                .data(getTasksList)
                .build();
    }

    @Override
    public ApiResponseDto<?> createTask(Task task) {
        // TODO: agregar validaciones

        Task insertedTask = taskRepository.save(task);

        return ApiResponseDto.builder()
                .statusCode(201)
                .message("Operacion exitosa: guardar")
                .data(insertedTask)
                .build();
    }

    @Override
    public ApiResponseDto<?> updateTask(Long id, Task task) {
        // TODO: Agregar validaciones

        Task updateTask = taskRepository.save(task);

        return ApiResponseDto.builder()
                .statusCode(201)
                .message("Operacion exitosa: actualizar")
                .data(updateTask)
                .build();
    }

    @Override
    public ApiResponseDto<?> deleteTask(Long id) {

        return ApiResponseDto.builder()
                .statusCode(201)
                .message("Operacion exitosa: eliminar")
                .data(id)
                .build();
    }

}
