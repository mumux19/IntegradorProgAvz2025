package com.integrador.project.controller;

import input.DeleteProjectInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectDeleteController {

    private final DeleteProjectInput deleteProjectInput;

    @Autowired
    public ProjectDeleteController(DeleteProjectInput deleteProjectInput) {
        this.deleteProjectInput = deleteProjectInput;
    }

    @DeleteMapping("/{projectId}")
    public boolean deleteProject(@PathVariable Long projectId) throws Exception {
        return deleteProjectInput.deleteProject(projectId);
    }
}
