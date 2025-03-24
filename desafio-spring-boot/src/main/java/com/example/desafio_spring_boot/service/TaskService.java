package com.example.desafio_spring_boot.service;

import com.example.desafio_spring_boot.entity.Task;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;

public interface TaskService {

    ApiResponseDto<?> getAllTask();

    ApiResponseDto<?> createTask(Task task);

    ApiResponseDto<?> updateTask(Long id, Task task);

    ApiResponseDto<?> deleteTask(Long id);

}
