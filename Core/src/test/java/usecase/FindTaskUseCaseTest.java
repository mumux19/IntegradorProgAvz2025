package usecase;

import exception.TaskUseCaseException;
import model.Project;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindTaskUseCaseTest {

    @Mock
    TaskOutPut taskOutPut;

    Clock fixedClock = Clock.fixed(
            LocalDateTime.of(2025, 11, 11, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );

    @Test
    public void findTasksSuccess() {
        Project project = Project.create("Website", LocalDate.now(), LocalDate.now().plusDays(10), null, "desc");
        Task task1 = Task.create(project, "Task 1", 5, "Alice", TaskStatus.ACTIVE, fixedClock);
        Task task2 = Task.create(project, "Task 2", 8, "Bob", TaskStatus.ACTIVE, fixedClock);

        when(taskOutPut.findTasks(project.getId(), 5, null)).thenReturn(List.of(task1, task2));

        FindTaskUseCase useCase = new FindTaskUseCase(taskOutPut);

        List<Task> result = useCase.findTasks(project.getId(), 5, null);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(task1));
        Assertions.assertTrue(result.contains(task2));
    }

    @Test
    public void findTasksNoProjectId() {
        FindTaskUseCase useCase = new FindTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                useCase.findTasks(null, 5, "Alice")
        );

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                useCase.findTasks(0L, 5, "Alice")
        );
    }

    @Test
    public void findTasksEmptyList() {
        Project project = Project.create("Website", LocalDate.now(), LocalDate.now().plusDays(10), null, "desc");

        when(taskOutPut.findTasks(project.getId(), null, null)).thenReturn(List.of());

        FindTaskUseCase useCase = new FindTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                useCase.findTasks(project.getId(), null, null)
        );
    }

    @Test
    public void findTaskByIdSuccess() {
        Project project = Project.create("Website", LocalDate.now(), LocalDate.now().plusDays(10), null, "desc");
        Task task = Task.create(project, "Task 1", 5, "Alice", TaskStatus.ACTIVE, fixedClock);

        when(taskOutPut.findTaskById(10L)).thenReturn(task);

        FindTaskUseCase useCase = new FindTaskUseCase(taskOutPut);

        Task result = useCase.findTaskById(10L);

        Assertions.assertEquals(task, result);
    }

    @Test
    public void findTaskByIdNotFound() {
        when(taskOutPut.findTaskById(99L)).thenReturn(null);

        FindTaskUseCase useCase = new FindTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                useCase.findTaskById(99L)
        );
    }

    @Test
    public void findTaskByIdInvalidId() {
        FindTaskUseCase useCase = new FindTaskUseCase(taskOutPut);

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                useCase.findTaskById(0L)
        );

        Assertions.assertThrows(TaskUseCaseException.class, () ->
                useCase.findTaskById(-5L)
        );
    }
}
