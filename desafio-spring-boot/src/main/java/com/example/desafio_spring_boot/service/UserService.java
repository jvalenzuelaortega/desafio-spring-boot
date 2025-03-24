package com.example.desafio_spring_boot.service;

import com.example.desafio_spring_boot.entity.User;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;

public interface UserService {

    ApiResponseDto<?> getAllUsers();

    ApiResponseDto<?> createUser(User user);

    ApiResponseDto<?> updateUser(Long id, User user);

    ApiResponseDto<?> deleteUser(Long id);
}
