package com.example.desafio_spring_boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_spring_boot.model.request.TaskRequestDto;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.service.TaskService;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/get-all-tasks", produces="application/json")
    public ResponseEntity<ApiResponseDto<?>> getAllTask() {
        ApiResponseDto<?> getAllTask = taskService.getAllTask();
        return ResponseEntity.status(HttpStatus.OK).body(getAllTask);
    }

    @PostMapping(value = "/create-task", produces="application/json")
    public ResponseEntity<ApiResponseDto<?>> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        ApiResponseDto<?> createTask = taskService.createTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTask);
    }

    @PutMapping(value = "/update-task/{id}", produces="application/json")
    public ResponseEntity<ApiResponseDto<?>> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        ApiResponseDto<?> updateTask = taskService.updateTask(id, taskRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateTask);
    }

    @DeleteMapping(value = "/delete-task/{id}", produces="application/json")
    public ResponseEntity<ApiResponseDto<?>> deleteTask(@PathVariable Long id) {
        ApiResponseDto<?> deleteTask = taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteTask);
    }
}
