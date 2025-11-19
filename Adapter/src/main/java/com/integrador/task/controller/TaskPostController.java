package com.integrador.task.controller;

import com.integrador.task.Crud.TaskCrud;
import input.CreateTaskInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskPostController {
    private CreateTaskInput createTaskInput;

    @Autowired
    public TaskPostController(CreateTaskInput createTaskInput) {
        this.createTaskInput = createTaskInput;
    }


}
