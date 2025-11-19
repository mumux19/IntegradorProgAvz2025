package com.integrador.task;
import com.integrador.task.controller.TaskGetController;
import exception.TaskUseCaseException;
import input.FindTaskInput;
import model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskGetControllerTest {

    @Mock
    private FindTaskInput findTaskInput;

    @InjectMocks
    private TaskGetController controller;

    @Test
    public void getTasksSuccess() throws Exception {
        Task task1 = new Task();
        Task task2 = new Task();
        List<Task> mockTasks = Arrays.asList(task1, task2);

        when(findTaskInput.findTasks(1L, null, null)).thenReturn(mockTasks);

        ResponseEntity<?> response = controller.getTasks(1L, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTasks, response.getBody());
        verify(findTaskInput, times(1)).findTasks(1L, null, null);
    }

    @Test
    public void getTasksWithFilters() throws Exception {
        Task task = new Task();
        List<Task> mockTasks = List.of(task);

        when(findTaskInput.findTasks(1L, 5, "John")).thenReturn(mockTasks);

        ResponseEntity<?> response = controller.getTasks(1L, 5, "John");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTasks, response.getBody());
        verify(findTaskInput, times(1)).findTasks(1L, 5, "John");
    }

    @Test
    public void getTasksNotFound() throws Exception {
        when(findTaskInput.findTasks(1L, null, null))
                .thenThrow(new TaskUseCaseException("Project not found"));

        ResponseEntity<?> response = controller.getTasks(1L, null, null);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Project not found", response.getBody());
        verify(findTaskInput, times(1)).findTasks(1L, null, null);
    }

    @Test
    public void getTasksInternalServerError() throws Exception {
        when(findTaskInput.findTasks(1L, null, null))
                .thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = controller.getTasks(1L, null, null);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Internal server error", response.getBody());
        verify(findTaskInput, times(1)).findTasks(1L, null, null);
    }
}