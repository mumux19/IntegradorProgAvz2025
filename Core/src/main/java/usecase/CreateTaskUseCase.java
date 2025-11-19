package usecase;

import exception.TaskUseCaseException;
import input.CreateTaskInput;
import model.Project;
import model.ProjectStatus;
import model.Task;
import model.TaskStatus;
import output.ProjectOutPut;
import output.TaskOutPut;

import java.time.Clock;

public class CreateTaskUseCase implements CreateTaskInput {
    private final TaskOutPut taskOutPut;
    private final ProjectOutPut projectOutPut;

    public CreateTaskUseCase(TaskOutPut taskOutPut, ProjectOutPut projectOutPut, Clock clock) {
        this.taskOutPut = taskOutPut;
        this.projectOutPut = projectOutPut;
    }

    @Override
    public boolean createTask(Project project, String title, Integer estimateHours,
                              String assignee, TaskStatus status, Clock clock) {

        if (!projectOutPut.existsById(project.getId()))
            throw new TaskUseCaseException("Project does not exist");

        Project stored = projectOutPut.findById(project.getId()).orElseThrow(
                () -> new TaskUseCaseException("Project not found"));

        if (stored.getStatus() == ProjectStatus.CLOSED)
            throw new TaskUseCaseException("Cannot add task to CLOSED project");

        if (taskOutPut.validateTitle(title))
            throw new TaskUseCaseException("Task title already exists");

        Task task = Task.create(stored, title, estimateHours, assignee, status, clock);

        if (!taskOutPut.saveTask(task))
            throw new TaskUseCaseException("Failed to save task");

        return true;
    }
}