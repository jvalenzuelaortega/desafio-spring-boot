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

import com.example.desafio_spring_boot.entity.Task;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.service.TaskService;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get-all-tasks")
    public ResponseEntity<ApiResponseDto<?>> getAllTask() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @PostMapping("/create-task")
    public ResponseEntity<ApiResponseDto<?>> createTask(@RequestBody Task tarea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(tarea));
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateTask(@PathVariable Long id, @RequestBody Task tarea) {
        return ResponseEntity.ok(taskService.updateTask(id, tarea));
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
