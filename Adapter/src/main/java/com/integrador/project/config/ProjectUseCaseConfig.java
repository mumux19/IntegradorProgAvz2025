package com.integrador.project.config;

import input.CreateProjectInput;
import input.DeleteProjectInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import output.ProjectOutPut;
import output.TaskOutPut;
import usecase.CreateProjectUseCase;
import usecase.DeleteProjectUseCase;

@Configuration
public class ProjectUseCaseConfig {

    @Bean
    public CreateProjectInput projectInput(ProjectOutPut projectOutPut) {
        return new CreateProjectUseCase(projectOutPut);
    }

    @Bean
    public DeleteProjectInput deleteProjectInput(ProjectOutPut projectOutPut, TaskOutPut taskOutPut) {
        return new DeleteProjectUseCase(projectOutPut, taskOutPut);
    }
}