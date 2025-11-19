package com.integrador.project.controller;

import exception.ProjectUseCaseException;
import input.DeleteProjectInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectDeleteController {

    private final DeleteProjectInput deleteProjectInput;

    @Autowired
    public ProjectDeleteController(DeleteProjectInput deleteProjectInput) {
        this.deleteProjectInput = deleteProjectInput;
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        try {
            boolean deleted = deleteProjectInput.deleteProject(projectId);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Project deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (ProjectUseCaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

