package usecase;

import exception.TaskUseCaseException;
import input.CreateProjectInput;
import input.CreateTaskInput;
import model.Project;
import model.Task;
import model.TaskStatus;
import output.TaskOutPut;

import java.time.Clock;

public class CreateTaskUseCase implements CreateTaskInput {
    TaskOutPut taskOutPut;

    public CreateTaskUseCase(TaskOutPut taskOutPut) {
        this.taskOutPut = taskOutPut;
    }


    @Override
    public boolean createTask(Long id, String title, Integer estimateHours, String assignee, TaskStatus status, Clock clock) {
        Task task = Task.create(id,title, estimateHours, assignee, status, clock);

        if (taskOutPut.validateTitle(title)) {
            throw new TaskUseCaseException("The title already exists");
        }

        if (taskOutPut.saveTask(task)) {
            return true;

        }


        throw new TaskUseCaseException("Error saving task");
    }


}
