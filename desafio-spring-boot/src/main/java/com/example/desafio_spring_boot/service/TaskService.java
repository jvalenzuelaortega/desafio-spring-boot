package com.example.desafio_spring_boot.service;

import com.example.desafio_spring_boot.model.request.TaskRequestDto;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;

public interface TaskService {

    ApiResponseDto<?> getAllTask();

    ApiResponseDto<?> createTask(TaskRequestDto taskRequestDto);

    ApiResponseDto<?> updateTask(Long id, TaskRequestDto taskRequestDto);

    ApiResponseDto<?> deleteTask(Long id);

}
