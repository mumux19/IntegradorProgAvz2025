package com.integrador.task.persistence;
import com.integrador.task.crud.TaskCrud;
import com.integrador.task.entity.data.TaskData;
import exception.TaskException;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskRepositoryTest {

    @Mock
    private TaskCrud taskCrud;

    @InjectMocks
    private TaskRepository repository;

    private Task task;
    private TaskData taskData;

    @BeforeEach
    void setUp() {
        task = new Task();
        taskData = new TaskData();
    }

    @Test
    void saveTaskSuccess() {
        boolean result = repository.saveTask(task);
        assertTrue(result);
        verify(taskCrud, times(1)).save(any(TaskData.class));
    }

    @Test
    void saveTaskNull_ThrowsException() {
        TaskException exception = assertThrows(TaskException.class, () -> repository.saveTask(null));
        assertEquals("Task cannot be null", exception.getMessage());
    }

    @Test
    void findTasksSuccess() {
        when(taskCrud.searchTasks(1L, null, null)).thenReturn(Arrays.asList(taskData));

        List<Task> result = repository.findTasks(1L, null, null);

        assertEquals(1, result.size());
        verify(taskCrud, times(1)).searchTasks(1L, null, null);
    }

    @Test
    void findTasksProjectIdNull_ThrowsException() {
        TaskException exception = assertThrows(TaskException.class, () -> repository.findTasks(null, null, null));
        assertEquals("Project ID must be provided", exception.getMessage());
    }

    @Test
    void findTaskByIdSuccess() {
        when(taskCrud.findById(1L)).thenReturn(Optional.of(taskData));

        Task result = repository.findTaskById(1L);

        assertNotNull(result);
        verify(taskCrud, times(1)).findById(1L);
    }

    @Test
    void findTaskByIdNull_ThrowsException() {
        TaskException exception = assertThrows(TaskException.class, () -> repository.findTaskById(null));
        assertEquals("Task ID must be provided", exception.getMessage());
    }

    @Test
    void findTaskByIdNotFound_ThrowsException() {
        when(taskCrud.findById(1L)).thenReturn(Optional.empty());

        TaskException exception = assertThrows(TaskException.class, () -> repository.findTaskById(1L));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    void deleteTaskByIdSuccess() {
        when(taskCrud.existsById(1L)).thenReturn(true);

        boolean result = repository.deleteTaskById(1L);

        assertTrue(result);
        verify(taskCrud, times(1)).deleteById(1L);
    }

    @Test
    void deleteTaskByIdNotFound_ThrowsException() {
        when(taskCrud.existsById(1L)).thenReturn(false);

        TaskException exception = assertThrows(TaskException.class, () -> repository.deleteTaskById(1L));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    void validateTitleSuccess() {
        when(taskCrud.existsByTitle("Task 1")).thenReturn(true);

        boolean result = repository.validateTitle("Task 1");

        assertTrue(result);
        verify(taskCrud, times(1)).existsByTitle("Task 1");
    }

    @Test
    void validateTitleNullOrEmpty_ThrowsException() {
        TaskException exception1 = assertThrows(TaskException.class, () -> repository.validateTitle(null));
        assertEquals("Title cannot be null or empty", exception1.getMessage());

        TaskException exception2 = assertThrows(TaskException.class, () -> repository.validateTitle(""));
        assertEquals("Title cannot be null or empty", exception2.getMessage());
    }

    @Test
    void countTasksByProjectIdSuccess() {
        when(taskCrud.countByProjectId(1L)).thenReturn(5);

        int result = repository.countTasksByProjectId(1L);

        assertEquals(5, result);
        verify(taskCrud, times(1)).countByProjectId(1L);
    }

    @Test
    void countTasksByProjectIdNull_ThrowsException() {
        TaskException exception = assertThrows(TaskException.class, () -> repository.countTasksByProjectId(null));
        assertEquals("Project ID must be provided", exception.getMessage());
    }
}