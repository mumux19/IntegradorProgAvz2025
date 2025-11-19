package com.integrador.task;

import com.integrador.task.controller.TaskPostController;
import com.integrador.task.entity.dto.TaskDTO;
import input.CreateTaskInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskPostControllerTest {

    @Mock
    private CreateTaskInput createTaskInput;

    @Mock
    private Clock clock;

    @InjectMocks
    private TaskPostController controller;

    @Test
    public void createTaskSuccess() throws Exception {
        TaskDTO dto = new TaskDTO();
        dto.setProject(1L);
        dto.setTitle("Task 1");
        dto.setEstimateHours(5);
        dto.setAssignee("John");
        dto.setStatus("OPEN");

        when(createTaskInput.createTask(
                dto.getProject(),
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getAssignee(),
                dto.getStatus(),
                clock
        )).thenReturn(true);

        ResponseEntity<?> response = controller.createTask(dto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Created Successfully", response.getBody());
        verify(createTaskInput, times(1)).createTask(
                dto.getProject(),
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getAssignee(),
                dto.getStatus(),
                clock
        );
    }

    @Test
    public void createTaskFailure() throws Exception {
        TaskDTO dto = new TaskDTO();
        dto.setProject(1L);
        dto.setTitle("Task 1");
        dto.setEstimateHours(5);
        dto.setAssignee("John");
        dto.setStatus("OPEN");

        when(createTaskInput.createTask(
                dto.getProject(),
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getAssignee(),
                dto.getStatus(),
                clock
        )).thenReturn(false);

        ResponseEntity<?> response = controller.createTask(dto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Creation Failed", response.getBody());
        verify(createTaskInput, times(1)).createTask(
                dto.getProject(),
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getAssignee(),
                dto.getStatus(),
                clock
        );
    }

    @Test
    public void createTaskException() throws Exception {
        TaskDTO dto = new TaskDTO();
        dto.setProject(1L);
        dto.setTitle("Task 1");
        dto.setEstimateHours(5);
        dto.setAssignee("John");
        dto.setStatus("OPEN");

        when(createTaskInput.createTask(
                dto.getProject(),
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getAssignee(),
                dto.getStatus(),
                clock
        )).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = controller.createTask(dto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Unexpected error", response.getBody());
        verify(createTaskInput, times(1)).createTask(
                dto.getProject(),
                dto.getTitle(),
                dto.getEstimateHours(),
                dto.getAssignee(),
                dto.getStatus(),
                clock
        );
    }
}