package com.integrador.task;

import com.integrador.task.controller.TaskDeleteController;
import exception.TaskUseCaseException;
import input.DeleteTaskInput;
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
public class TaskDeleteControllerTest {

    @Mock
    private DeleteTaskInput deleteTaskInput;

    @InjectMocks
    private TaskDeleteController controller;

    @Test
    public void deleteTaskSuccess() throws Exception {
        when(deleteTaskInput.deleteTask(1L, 1L)).thenReturn(true);

        ResponseEntity<?> response = controller.deleteTask(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Task deleted successfully", response.getBody());
        verify(deleteTaskInput, times(1)).deleteTask(1L, 1L);
    }

    @Test
    public void deleteTaskNotFound() throws Exception {
        when(deleteTaskInput.deleteTask(1L, 2L)).thenReturn(false);

        ResponseEntity<?> response = controller.deleteTask(1L, 2L);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Task not found or invalid project", response.getBody());
        verify(deleteTaskInput, times(1)).deleteTask(1L, 2L);
    }

    @Test
    public void deleteTaskConflict() throws Exception {
        when(deleteTaskInput.deleteTask(1L, 3L)).thenThrow(new TaskUseCaseException("Conflict deleting task"));

        ResponseEntity<?> response = controller.deleteTask(1L, 3L);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Conflict deleting task", response.getBody());
        verify(deleteTaskInput, times(1)).deleteTask(1L, 3L);
    }

    @Test
    public void deleteTaskInternalServerError() throws Exception {
        when(deleteTaskInput.deleteTask(1L, 4L)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = controller.deleteTask(1L, 4L);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Internal server error", response.getBody());
        verify(deleteTaskInput, times(1)).deleteTask(1L, 4L);
    }
}