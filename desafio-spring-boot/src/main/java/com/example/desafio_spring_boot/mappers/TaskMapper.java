package com.example.desafio_spring_boot.mappers;

import com.example.desafio_spring_boot.entity.Task;
import com.example.desafio_spring_boot.model.response.task.TaskResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskResponseDto mapperTaskEntitytoTaskResponseDto(Task task) {
        return modelMapper.map(task, TaskResponseDto.class);
    }

    public List<TaskResponseDto> mapperTaskEntitytoTaskResponseDtoList(List<Task> tasks) {
        return tasks.stream().map(this::mapperTaskEntitytoTaskResponseDto).toList();
    }
}
