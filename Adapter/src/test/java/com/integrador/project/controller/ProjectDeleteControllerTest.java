package com.integrador.project.controller;

import exception.ProjectUseCaseException;
import input.DeleteProjectInput;
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
public class ProjectDeleteControllerTest {

    @Mock
    private DeleteProjectInput deleteProjectInput;

    @InjectMocks
    private ProjectDeleteController controller;

    @Test
    public void deleteProjectSuccess() throws Exception {

        when(deleteProjectInput.deleteProject(1L)).thenReturn(true);


        ResponseEntity<?> response = controller.deleteProject(1L);


        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Project deleted successfully", response.getBody());
        verify(deleteProjectInput, times(1)).deleteProject(1L);
    }

    @Test
    public void deleteProjectConflict() throws Exception {
        when(deleteProjectInput.deleteProject(2L)).thenReturn(false);

        ResponseEntity<?> response = controller.deleteProject(2L);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(deleteProjectInput, times(1)).deleteProject(2L);
    }

    @Test
    public void deleteProjectNotFound() throws Exception {
        when(deleteProjectInput.deleteProject(3L)).thenThrow(new ProjectUseCaseException("Not found"));

        ResponseEntity<?> response = controller.deleteProject(3L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(deleteProjectInput, times(1)).deleteProject(3L);
    }

    @Test
    public void deleteProjectInternalError() throws Exception {
        when(deleteProjectInput.deleteProject(4L)).thenThrow(new RuntimeException("Unexpected"));

        ResponseEntity<?> response = controller.deleteProject(4L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(deleteProjectInput, times(1)).deleteProject(4L);
    }
}
