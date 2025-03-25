package com.example.desafio_spring_boot.service;

import com.example.desafio_spring_boot.model.request.UserRequestDto;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;

public interface UserService {

    ApiResponseDto<?> getAllUsers();

    ApiResponseDto<?> createUser(UserRequestDto userRequestDto);

}
