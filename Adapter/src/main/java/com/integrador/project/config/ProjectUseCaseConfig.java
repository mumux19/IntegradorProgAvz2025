package com.integrador.project.config;


import input.CreateProjectInput;
import input.DeleteProjectInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import output.ProjectOutPut;
import usecase.CreateProjectUseCase;
import usecase.DeleteProjectUseCase;

@Configuration
public class ProjectUseCaseConfig {
    @Bean
    public CreateProjectInput projectInput(ProjectOutPut p) {
        return new CreateProjectUseCase(p);
    }

    @Bean
    public DeleteProjectInput deleteProjectInput(ProjectOutPut p) {
        return new DeleteProjectUseCase(p);
    }
}
