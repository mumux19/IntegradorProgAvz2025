package com.integrador.task.controller;

import com.integrador.task.entity.dto.TaskDTO;
import input.CreateTaskInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
@RequestMapping("/tasks")
public class TaskPostController {

    private final CreateTaskInput createTaskInput;
    private final Clock clock;

    @Autowired
    public TaskPostController(CreateTaskInput createTaskInput, Clock clock) {
        this.createTaskInput = createTaskInput;
        this.clock = clock;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            return createTaskInput.createTask(
                    taskDTO.getProject(),
                    taskDTO.getTitle(),
                    taskDTO.getEstimateHours(),
                    taskDTO.getAssignee(),
                    taskDTO.getStatus(),
                    clock
            ) ? ResponseEntity.status(201).body("Created Successfully")
                    : ResponseEntity.status(400).body("Creation Failed");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}