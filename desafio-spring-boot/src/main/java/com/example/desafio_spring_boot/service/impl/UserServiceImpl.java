package com.example.desafio_spring_boot.service.impl;

import java.util.List;

import com.example.desafio_spring_boot.mappers.UserMapper;
import com.example.desafio_spring_boot.model.request.UserRequestDto;
import org.springframework.stereotype.Service;

import com.example.desafio_spring_boot.entity.User;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.repository.UserRepository;
import com.example.desafio_spring_boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ApiResponseDto<?> getAllUsers() {
        List<User> getUsersList = userRepository.findAll();
        if (getUsersList.isEmpty()) {
            return ApiResponseDto.builder().statusCode(404).message("No hay datos de listas").data(null).build();
        }

        return ApiResponseDto.builder().statusCode(200).message("Operacion exitosa: listado").data(getUsersList).build();
    }

    @Override
    public ApiResponseDto<?> createUser(UserRequestDto userRequestDto) {
        User user = userMapper.mapperUserRequestDtoToUserEntity(userRequestDto);
        User savedUser = userRepository.save(user);
        return ApiResponseDto.builder().statusCode(201).message("Operacion exitosa: guardar").data(savedUser).build();
    }

}
