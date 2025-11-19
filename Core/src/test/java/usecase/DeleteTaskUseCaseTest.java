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
import output.TaskOutPut;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskUseCaseTest {

    @Mock
    TaskOutPut taskOutPut;

    @Test
    public void deleteTaskSuccess() {
        Long projectId = 1L;
        Long taskId = 10L;

        Project project = Project.create("Project A",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Desc");

        Clock clock = Clock.fixed(LocalDateTime.of(2025, 11, 11, 10, 0)
                .toInstant(ZoneOffset.UTC), ZoneOffset.UTC);

        Task task = Task.create(project, "Task A", 5, "Alice", TaskStatus.ACTIVE, clock);

        when(taskOutPut.findTaskById(taskId)).thenReturn(task);
        when(taskOutPut.deleteTaskById(taskId)).thenReturn(true);

        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);

        boolean resultado = deleteTaskUseCase.deleteTask(projectId, taskId);

        Assertions.assertTrue(resultado);
        verify(taskOutPut).deleteTaskById(taskId);
    }

    @Test
    public void deleteTaskNotExists() {
        Long projectId = 1L;
        Long taskId = 10L;

        when(taskOutPut.findTaskById(taskId)).thenReturn(null);

        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                deleteTaskUseCase.deleteTask(projectId, taskId));
    }

    @Test
    public void deleteTaskWrongProject() {
        Long projectId = 1L;
        Long taskId = 10L;

        Project project = Project.create("Project A",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Desc");

        Project otherProject = Project.create("Project B",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Desc");

        Clock clock = Clock.fixed(LocalDateTime.of(2025, 11, 11, 10, 0)
                .toInstant(ZoneOffset.UTC), ZoneOffset.UTC);

        Task task = Task.create(otherProject, "Task A", 5, "Alice", TaskStatus.ACTIVE, clock);

        when(taskOutPut.findTaskById(taskId)).thenReturn(task);

        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                deleteTaskUseCase.deleteTask(projectId, taskId));
    }

    @Test
    public void deleteTaskFailure() {
        Long projectId = 1L;
        Long taskId = 10L;

        Project project = Project.create("Project A",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Desc");

        Clock clock = Clock.fixed(LocalDateTime.of(2025, 11, 11, 10, 0)
                .toInstant(ZoneOffset.UTC), ZoneOffset.UTC);

        Task task = Task.create(project, "Task A", 5, "Alice", TaskStatus.ACTIVE, clock);

        when(taskOutPut.findTaskById(taskId)).thenReturn(task);
        when(taskOutPut.deleteTaskById(taskId)).thenReturn(false);

        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                deleteTaskUseCase.deleteTask(projectId, taskId));
    }
}