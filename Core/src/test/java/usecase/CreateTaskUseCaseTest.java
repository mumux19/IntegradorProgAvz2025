package usecase;

import exception.TaskUseCaseException;
import model.Project;
import model.ProjectStatus;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectOutPut;
import output.TaskOutPut;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {

    @Mock
    TaskOutPut taskOutPut;

    @Mock
    ProjectOutPut projectOutPut;

    Clock fixedClock = Clock.fixed(
            LocalDateTime.of(2025, 11, 11, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );

    @Test
    public void createTaskSuccess() {
        Project project = Project.create("Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign website");

        when(projectOutPut.existsById(project.getId())).thenReturn(true);
        when(projectOutPut.findById(project.getId())).thenReturn(Optional.of(project));
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(false);
        when(taskOutPut.saveTask(any(Task.class))).thenReturn(true);

        CreateTaskUseCase useCase = new CreateTaskUseCase(taskOutPut, projectOutPut, clock);
        boolean result = useCase.createTask(project, "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock);

        Assertions.assertTrue(result);
    }

    @Test
    public void createTaskProjectDoesNotExist() {
        Project project = Project.create("Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign website");

        when(projectOutPut.existsById(project.getId())).thenReturn(false);

        CreateTaskUseCase useCase = new CreateTaskUseCase(taskOutPut, projectOutPut, clock);

        Assertions.assertThrows(TaskUseCaseException.class,
                () -> useCase.createTask(project, "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));
    }

    @Test
    public void createTaskTitleExists() {
        Project project = Project.create("Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign website");

        when(projectOutPut.existsById(project.getId())).thenReturn(true);
        when(projectOutPut.findById(project.getId())).thenReturn(Optional.of(project));
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(true);

        CreateTaskUseCase useCase = new CreateTaskUseCase(taskOutPut, projectOutPut, clock);

        Assertions.assertThrows(TaskUseCaseException.class,
                () -> useCase.createTask(project, "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));
    }

    @Test
    public void createTaskClosedProject() {
        Project project = Project.create("Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.CLOSED,
                "Redesign website");

        when(projectOutPut.existsById(project.getId())).thenReturn(true);
        when(projectOutPut.findById(project.getId())).thenReturn(Optional.of(project));

        CreateTaskUseCase useCase = new CreateTaskUseCase(taskOutPut, projectOutPut, clock);

        Assertions.assertThrows(TaskUseCaseException.class,
                () -> useCase.createTask(project, "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));
    }

    @Test
    public void createTaskSaveFails() {
        Project project = Project.create("Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign website");

        when(projectOutPut.existsById(project.getId())).thenReturn(true);
        when(projectOutPut.findById(project.getId())).thenReturn(Optional.of(project));
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(false);
        when(taskOutPut.saveTask(any(Task.class))).thenReturn(false);

        CreateTaskUseCase useCase = new CreateTaskUseCase(taskOutPut, projectOutPut, clock);

        Assertions.assertThrows(TaskUseCaseException.class,
                () -> useCase.createTask(project, "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));
    }
}