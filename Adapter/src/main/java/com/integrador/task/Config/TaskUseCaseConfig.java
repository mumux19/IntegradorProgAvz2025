package com.integrador.task.Config;

import input.CreateTaskInput;
import input.DeleteTaskInput;
import input.FindTaskInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import output.ProjectOutPut;
import output.TaskOutPut;
import usecase.CreateTaskUseCase;
import usecase.DeleteTaskUseCase;
import usecase.FindTaskUseCase;

import java.time.Clock;

@Configuration
public class TaskUseCaseConfig {

    @Bean
    public CreateTaskInput taskInput(TaskOutPut t, ProjectOutPut p, Clock clock) {
        return new CreateTaskUseCase(t, p, clock);
    }

    @Bean
    public DeleteTaskInput deleteTaskInput(TaskOutPut t) {
        return new DeleteTaskUseCase(t);
    }

    @Bean
    public FindTaskInput getTaskInput(TaskOutPut t) {
        return new FindTaskUseCase(t);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}