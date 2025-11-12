package usecase;

import exception.TaskException;
import exception.TaskUseCaseException;
import input.DeleteProjectInput;
import input.DeleteTaskInput;
import output.TaskOutPut;

public class DeleteTaskUseCase implements DeleteTaskInput {
    TaskOutPut taskOutPut;
    public DeleteTaskUseCase(TaskOutPut taskOutPut) {
        this.taskOutPut = taskOutPut;
    }


    @Override
    public boolean deleteTask(String title) throws Exception {
        if(!taskOutPut.validateTitle(title)){
            throw new TaskUseCaseException("There is no task with that name");
        }

        if(!taskOutPut.deleteTask(title)){
            throw new TaskUseCaseException("Error deleting task");
        }

        return true;
    }


}
