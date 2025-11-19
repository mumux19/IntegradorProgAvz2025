package com.integrador.task.controller;

import exception.TaskUseCaseException;
import input.FindTaskInput;

import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskGetController {

    private final FindTaskInput findTaskInput;

    @Autowired
    public TaskGetController(FindTaskInput findTaskInput) {
        this.findTaskInput = findTaskInput;
    }

    @GetMapping
    public ResponseEntity<?> getTasks(
            @PathVariable Long projectId,
            @RequestParam(required = false) Integer minEstimate,
            @RequestParam(required = false) String assignee
    ) {
        try {
            List<Task> tasks = findTaskInput.findTasks(projectId, minEstimate, assignee);
            return ResponseEntity.ok(tasks);
        } catch (TaskUseCaseException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}