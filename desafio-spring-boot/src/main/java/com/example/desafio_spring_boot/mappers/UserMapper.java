package com.example.desafio_spring_boot.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.desafio_spring_boot.entity.User;
import com.example.desafio_spring_boot.model.request.UserRequestDto;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User mapperUserRequestDtoToUserEntity(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        return user;
    }

}
