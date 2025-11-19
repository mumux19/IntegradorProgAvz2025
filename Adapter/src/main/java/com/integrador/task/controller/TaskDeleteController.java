package com.integrador.task.controller;

import exception.TaskUseCaseException;
import input.DeleteTaskInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskDeleteController {

    private final DeleteTaskInput deleteTaskInput;

    @Autowired
    public TaskDeleteController(DeleteTaskInput deleteTaskInput) {
        this.deleteTaskInput = deleteTaskInput;
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId) {
        try {
            return deleteTaskInput.deleteTask(projectId, taskId)
                    ? ResponseEntity.ok("Task deleted successfully")
                    : ResponseEntity.status(404).body("Task not found or invalid project");

        } catch (TaskUseCaseException e) {

            return ResponseEntity.status(409).body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}