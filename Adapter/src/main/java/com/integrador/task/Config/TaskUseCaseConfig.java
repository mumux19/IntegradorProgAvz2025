package com.integrador.task.Config;

import input.CreateTaskInput;
import input.DeleteTaskInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import output.TaskOutPut;
import usecase.CreateTaskUseCase;
import usecase.DeleteTaskUseCase;

@Configuration
public class TaskUseCaseConfig {

    @Bean
    public CreateTaskInput createTaskInput(TaskOutPut t) {
        return new CreateTaskUseCase(t);
    }

    @Bean
    public DeleteTaskInput deleteTaskInput(TaskOutPut t) {
        return new DeleteTaskUseCase(t);
    }


}
