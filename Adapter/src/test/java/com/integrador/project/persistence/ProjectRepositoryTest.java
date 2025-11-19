package com.integrador.project.persistence;

import com.integrador.project.crud.ProjectCrud;
import com.integrador.project.entity.data.ProjectData;
import exception.ProjectException;
import model.Project;
import output.TaskOutPut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectRepositoryTest {

    @Mock
    private ProjectCrud projectCrud;

    @Mock
    private TaskOutPut taskOutPut;

    @InjectMocks
    private ProjectRepository repository;

    private Project project;
    private ProjectData projectData;

    @BeforeEach
    void setUp() {
        project = new Project();
        projectData = new ProjectData();
    }

    @Test
    void saveProjectSuccess() {
        boolean result = repository.save(project);
        assertTrue(result);
        verify(projectCrud, times(1)).save(any(ProjectData.class));
    }

    @Test
    void saveProjectNull_ThrowsException() {
        ProjectException exception = assertThrows(ProjectException.class, () -> repository.save(null));
        assertEquals("Project cannot be null", exception.getMessage());
    }

    @Test
    void findByIdSuccess() {
        when(projectCrud.findById(1L)).thenReturn(Optional.of(projectData));

        Optional<Project> result = repository.findById(1L);

        assertTrue(result.isPresent());
        verify(projectCrud, times(1)).findById(1L);
    }

    @Test
    void findByIdNull_ThrowsException() {
        ProjectException exception = assertThrows(ProjectException.class, () -> repository.findById(null));
        assertEquals("Project ID must be provided", exception.getMessage());
    }

    @Test
    void existsByIdSuccess() {
        when(projectCrud.existsById(1L)).thenReturn(true);
        assertTrue(repository.existsById(1L));
        verify(projectCrud, times(1)).existsById(1L);
    }

    @Test
    void existsByIdNull_ThrowsException() {
        ProjectException exception = assertThrows(ProjectException.class, () -> repository.existsById(null));
        assertEquals("Project ID must be provided", exception.getMessage());
    }

    @Test
    void existsByNameSuccess() {
        when(projectCrud.existsByName("Test")).thenReturn(true);
        assertTrue(repository.existsByName("Test"));
        verify(projectCrud, times(1)).existsByName("Test");
    }

    @Test
    void existsByNameNullOrEmpty_ThrowsException() {
        ProjectException exception1 = assertThrows(ProjectException.class, () -> repository.existsByName(null));
        assertEquals("Project name must be provided", exception1.getMessage());

        ProjectException exception2 = assertThrows(ProjectException.class, () -> repository.existsByName(""));
        assertEquals("Project name must be provided", exception2.getMessage());
    }

    @Test
    void deleteByIdSuccess() {
        when(projectCrud.existsById(1L)).thenReturn(true);
        when(taskOutPut.countTasksByProjectId(1L)).thenReturn(0);

        boolean result = repository.deleteById(1L);

        assertTrue(result);
        verify(projectCrud, times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdNotFound_ThrowsException() {
        when(projectCrud.existsById(1L)).thenReturn(false);

        ProjectException exception = assertThrows(ProjectException.class, () -> repository.deleteById(1L));
        assertEquals("Project not found", exception.getMessage());
    }

    @Test
    void deleteByIdWithTasks_ThrowsException() {
        when(projectCrud.existsById(1L)).thenReturn(true);
        when(taskOutPut.countTasksByProjectId(1L)).thenReturn(5);

        ProjectException exception = assertThrows(ProjectException.class, () -> repository.deleteById(1L));
        assertEquals("Cannot delete project: it still has tasks", exception.getMessage());
    }

    @Test
    void deleteByIdNull_ThrowsException() {
        ProjectException exception = assertThrows(ProjectException.class, () -> repository.deleteById(null));
        assertEquals("Project ID must be provided", exception.getMessage());
    }
}
