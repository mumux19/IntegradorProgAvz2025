package com.integrador.project.controller;

import exception.DuplicateResourceException;
import exception.ProjectException;
import input.CreateProjectInput;
import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectPostController {

    private final CreateProjectInput createProjectInput;

    @Autowired
    public ProjectPostController(CreateProjectInput createProjectInput) {
        this.createProjectInput = createProjectInput;
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project dto) {
        try {
            Project created = createProjectInput.createProject(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Project created correctly");
        } catch (DuplicateResourceException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (ProjectException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
