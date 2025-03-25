package com.example.desafio_spring_boot.config.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    ModelMapper initModelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    ObjectMapper initObjectMapperBean() {
        return JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

}
