package usecase;

import exception.TaskUseCaseException;
import input.FindTaskInput;
import model.Task;
import output.TaskOutPut;

public class FindTaskUseCase implements FindTaskInput {

    TaskOutPut taskOutPut;

    public FindTaskUseCase(TaskOutPut taskOutPut) {
        this.taskOutPut = taskOutPut;
    }

    @Override
    public Task findTask(String title) {

        if(!taskOutPut.validateTitle(title)) {
            throw new TaskUseCaseException("Invalid title");
        }

        return taskOutPut.findTask(title);

    }
}
