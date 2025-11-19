package com.integrador.project.controller;

import exception.DuplicateResourceException;
import exception.ProjectException;
import input.CreateProjectInput;
import model.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectPostControllerTest {

    @Mock
    private CreateProjectInput createProjectInput;

    @InjectMocks
    private ProjectPostController controller;

    @Test
    public void createProjectSuccess() {
        Project dto = new Project();
        Project createdProject = new Project();
        when(createProjectInput.createProject(dto)).thenReturn(createdProject);

        ResponseEntity<?> response = controller.createProject(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Project created correctly", response.getBody());
        verify(createProjectInput, times(1)).createProject(dto);
    }

    @Test
    public void createProjectDuplicateResource() {
        Project dto = new Project();
        when(createProjectInput.createProject(dto)).thenThrow(new DuplicateResourceException("Project already exists"));

        ResponseEntity<?> response = controller.createProject(dto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Project already exists", response.getBody());
        verify(createProjectInput, times(1)).createProject(dto);
    }

    @Test
    public void createProjectBadRequest() {
        Project dto = new Project();
        when(createProjectInput.createProject(dto)).thenThrow(new ProjectException("Invalid project data"));

        ResponseEntity<?> response = controller.createProject(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid project data", response.getBody());
        verify(createProjectInput, times(1)).createProject(dto);
    }

    @Test
    public void createProjectInternalServerError()  {
        Project dto = new Project();
        when(createProjectInput.createProject(dto)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = controller.createProject(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody());
        verify(createProjectInput, times(1)).createProject(dto);
    }
}
