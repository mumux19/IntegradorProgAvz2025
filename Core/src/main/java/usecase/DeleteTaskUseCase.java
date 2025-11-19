package usecase;

import exception.TaskUseCaseException;
import input.DeleteTaskInput;
import model.Task;
import output.TaskOutPut;

public class DeleteTaskUseCase implements DeleteTaskInput {
    private final TaskOutPut taskOutPut;

    public DeleteTaskUseCase(TaskOutPut taskOutPut) {
        this.taskOutPut = taskOutPut;
    }

    @Override
    public boolean deleteTask(Long projectId, Long taskId) {
        Task task = taskOutPut.findTaskById(taskId);
        if (task == null) throw new TaskUseCaseException("Task not found");
        if (!task.getProject().getId().equals(projectId))
            throw new TaskUseCaseException("Task does not belong to project");
        return taskOutPut.deleteTaskById(taskId);
    }
}