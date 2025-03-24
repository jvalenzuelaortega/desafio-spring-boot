package com.example.desafio_spring_boot.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {

    private String username;
    private String password;
}
