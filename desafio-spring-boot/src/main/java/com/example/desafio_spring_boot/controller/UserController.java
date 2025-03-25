package com.example.desafio_spring_boot.controller;

import com.example.desafio_spring_boot.model.request.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/insert-user")
    public ResponseEntity<ApiResponseDto<?>> createUser(@RequestBody UserRequestDto userRequestDto) {
        ApiResponseDto<?> createUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

}
