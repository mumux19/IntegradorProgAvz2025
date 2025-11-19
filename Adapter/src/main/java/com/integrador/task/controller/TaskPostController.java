package com.integrador.task.controller;

import com.integrador.task.Crud.TaskCrud;
import com.integrador.task.entity.TaskDTO;
import input.CreateTaskInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskPostController {
    private CreateTaskInput createTaskInput;

    @Autowired
    public TaskPostController(CreateTaskInput createTaskInput) {
        this.createTaskInput = createTaskInput;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO dto, @PathVariable String projectId){
        if(createTaskInput.createTask(
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getStatus(),
                dto.getAssignee()
        ))
            return ResponseEntity.status(HttpStatus.CREATED).body("Created Successfully");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Creation Failed");

    }

}
