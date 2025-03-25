package com.example.desafio_spring_boot.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status_description")
    private String statusDescription;
}
