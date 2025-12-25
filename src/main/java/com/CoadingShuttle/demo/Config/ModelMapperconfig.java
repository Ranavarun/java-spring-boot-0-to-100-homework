package com.CoadingShuttle.demo.Config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperconfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
