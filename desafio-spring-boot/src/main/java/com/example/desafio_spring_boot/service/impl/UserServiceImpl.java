package com.example.desafio_spring_boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.desafio_spring_boot.entity.User;
import com.example.desafio_spring_boot.model.response.ApiResponseDto;
import com.example.desafio_spring_boot.repository.UserRepository;
import com.example.desafio_spring_boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponseDto<?> getAllUsers() {
        List<User> getUsersList = userRepository.findAll();
        if (getUsersList.isEmpty()) {
            return ApiResponseDto.builder()
                    .statusCode(404)
                    .message("No hay datos de listas")
                    .data(null)
                    .build();
        }

        return ApiResponseDto.builder()
                .statusCode(200)
                .message("Operacion exitosa: listado")
                .data(getUsersList)
                .build();
    }

    @Override
    public ApiResponseDto<?> createUser(User user) {
        // TODO: agregar validaciones

        User insertedUser = userRepository.save(user);

        return ApiResponseDto.builder()
                .statusCode(201)
                .message("Operacion exitosa: guardar")
                .data(insertedUser)
                .build();
    }

    @Override
    public ApiResponseDto<?> updateUser(Long id, User user) {

        // TODO: Agregar validaciones

        User updateUser = userRepository.save(user);

        return ApiResponseDto.builder()
                .statusCode(201)
                .message("Operacion exitosa: actualizar")
                .data(updateUser)
                .build();
    }

    @Override
    public ApiResponseDto<?> deleteUser(Long id) {

        return ApiResponseDto.builder()
                .statusCode(201)
                .message("Operacion exitosa: eliminar")
                .data(id)
                .build();
    }

}
