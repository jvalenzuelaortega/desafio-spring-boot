package com.example.desafio_spring_boot.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskRequestDto {

    private String username;
    private String description;
    private String statusDescription;
}
