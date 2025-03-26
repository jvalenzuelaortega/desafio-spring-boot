package com.example.desafio_spring_boot.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.desafio_spring_boot.exceptions.TaskOperationException;
import com.example.desafio_spring_boot.exceptions.UserOperationException;
import com.example.desafio_spring_boot.mappers.TaskMapper;
import com.example.desafio_spring_boot.patterns.cor.DescriptionValidator;
import com.example.desafio_spring_boot.patterns.cor.UsernameValidator;
import com.example.desafio_spring_boot.patterns.cor.Validator;
import com.example.desafio_spring_boot.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;

import com.example.desafio_spring_boot.entity.Task;
import com.example.desafio_spring_boot.entity.TaskStatus;
import com.example.desafio_spring_boot.entity.User;
import com.example.desafio_spring_boot.model.request.TaskRequestDto;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.repository.TaskRepository;
import com.example.desafio_spring_boot.repository.UserRepository;
import com.example.desafio_spring_boot.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final UserRepository userRepository;
    private final Validator<TaskRequestDto> validator;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskStatusRepository taskStatusRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;

        Validator<TaskRequestDto> usernameValidator = new UsernameValidator();
        Validator<TaskRequestDto> descriptionValidator = new DescriptionValidator();
        usernameValidator.setNext(descriptionValidator);

        this.validator = usernameValidator;
    }

    @Override
    public ApiResponseDto<?> getAllTask() {
        List<Task> getTasksList = taskRepository.findAll();
        if (getTasksList.isEmpty()) {
            return ApiResponseDto.builder().statusCode(404).message("No hay datos de listas").data(Collections.emptyList()).build();
        }

        return ApiResponseDto.builder().statusCode(200).message("Operacion exitosa").data(taskMapper.mapperTaskEntitytoTaskResponseDtoList(getTasksList)).build();
    }

    @Override
    public ApiResponseDto<?> createTask(TaskRequestDto taskRequestDto) {
        validator.validate(taskRequestDto);

        User user = userRepository.findByUserName(taskRequestDto.getUsername()).orElseThrow(() -> new UserOperationException("Usuario no encontrado"));
        TaskStatus taskStatus = taskStatusRepository.findTaskStatusByName(taskRequestDto.getStatusDescription()).orElseThrow(() -> new TaskOperationException("Status no encontrado"));

        Task task = new Task();
        task.setDescription(taskRequestDto.getDescription());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(taskStatus);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return ApiResponseDto.builder().statusCode(201).message("Operacion exitosa: guardar").data(taskMapper.mapperTaskEntitytoTaskResponseDto(savedTask)).build();
    }

    @Override
    public ApiResponseDto<?> updateTask(Long id, TaskRequestDto taskRequestDto) {
        validator.validate(taskRequestDto);

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskOperationException("Tarea no encontrada"));
        User user = userRepository.findByUserName(taskRequestDto.getUsername()).orElseThrow(() -> new UserOperationException("Usuario no encontrado"));
        TaskStatus taskStatus = taskStatusRepository.findTaskStatusByName(taskRequestDto.getStatusDescription()).orElseThrow(() -> new TaskOperationException("Status no encontrado"));

        task.setDescription(taskRequestDto.getDescription());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(taskStatus);
        task.setUser(user);

        Task updatedTask = taskRepository.save(task);

        return ApiResponseDto.builder().statusCode(200).message("Operacion exitosa: actualizar").data(taskMapper.mapperTaskEntitytoTaskResponseDto(updatedTask)).build();
    }

    @Override
    public ApiResponseDto<?> deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new TaskOperationException("Tarea no encontrada"));
        taskRepository.deleteById(id);

        return ApiResponseDto.builder().statusCode(200).message("Operacion exitosa: eliminar").data(id).build();
    }

}
