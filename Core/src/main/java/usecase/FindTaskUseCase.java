package usecase;

import exception.TaskUseCaseException;
import input.FindTaskInput;
import model.Task;
import output.TaskOutPut;

import java.util.List;

public class FindTaskUseCase implements FindTaskInput {

    TaskOutPut taskOutPut;

    public FindTaskUseCase(TaskOutPut taskOutPut) {

        this.taskOutPut = taskOutPut;
    }



    @Override
    public List<Task> findTasks(Long projectId, Integer minEstimate, String assignee) {
        if (projectId == null || projectId <= 0) {
            throw new TaskUseCaseException("Invalid project id");
        }

        List<Task> tasks = taskOutPut.findTasks(projectId, minEstimate, assignee);

        if (tasks == null || tasks.isEmpty()) {
            throw new TaskUseCaseException("No tasks found with given filters");
        }

        return tasks;
    }

    public Task findTaskById(long taskId) {
        if (taskId <= 0) {
            throw new TaskUseCaseException("Invalid task id");
        }

        Task task = taskOutPut.findTaskById(taskId);

        if (task == null) {
            throw new TaskUseCaseException("Task not found");
        }

        return task;
    }
}