package com.integrador.task.TaskRepositorio;

import input.CreateTaskInput;
import model.Task;
import model.TaskStatus;
import output.TaskOutPut;

import java.time.Clock;

public class CrudRepositorioTask implements TaskOutPut {

    private CrudRepositorioTask crudRepositorioTask;


    public CrudRepositorioTask(CrudRepositorioTask crudRepositorioTask) {
        this.crudRepositorioTask = crudRepositorioTask;
    }

    @Override
    public boolean validateTitle(String title) {
        return false;
    }

    @Override
    public boolean saveTask(Task task) {
        return false;
    }

    @Override
    public boolean deleteTask(String title) throws Exception {
        return false;
    }

    @Override
    public Task findTask(String title) {
        return null;
    }
}
