package com.integrador.task.TaskRepositorio;

import exception.TaskException;
import input.CreateTaskInput;
import model.Task;
import model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import output.TaskOutPut;

import java.time.Clock;

public class CrudRepositorioTask implements TaskOutPut {

    private CrudRepositorioTask crudRepositorioTask;


    public CrudRepositorioTask(CrudRepositorioTask crudRepositorioTask) {
        this.crudRepositorioTask = crudRepositorioTask;
    }

    @Override
    public boolean validateTitle(String title) {
        return crudRepositorioTask.validateTitle(title);
    }

    @Override
    public boolean saveTask(Task task) {
        return crudRepositorioTask.saveTask(task);
    }

    @Override
    public boolean deleteTask(String title) throws TaskException {
        try {

           return crudRepositorioTask.deleteTask(title);
        } catch (Exception e) {
            throw new TaskException("Error deleting task");
        }

    }

    @Override
    public Task findTask(String title) {
        return null;
    }
}
