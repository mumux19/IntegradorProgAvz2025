package com.integrador.project.controller;

import com.integrador.project.crud.ProjectCrud;
import com.integrador.project.entity.data.ProjectData;
import com.integrador.project.entity.dto.ProjectDTO;
import com.integrador.project.mapper.ProjectMapper;
import input.CreateProjectInput;
import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectPostController {
    private final CreateProjectInput createProjectInput;

    @Autowired
    public ProjectPostController(CreateProjectInput createProjectInput) {
        this.createProjectInput = createProjectInput;
    }


    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO dto) {
        if( createProjectInput.createProject(
                dto.getName(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getStatus(),
                dto.getDescription()
        ))
            return ResponseEntity.status(HttpStatus.CREATED).body("Created Successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Creation Failed");


    }
}