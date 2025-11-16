package usecase;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.TaskOutPut;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CreateTaskUseCaseTest {
    long randomId = ThreadLocalRandom.current().nextLong(1, 1000);
    Clock fixedClock = Clock.fixed(
            LocalDateTime.of(2025, 11, 11, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );
    @Mock
    TaskOutPut taskOutPut;

    @Test
    public void CreateTaskTrue() {
        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(taskOutPut);

        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(false);
        when(taskOutPut.saveTask(any(Task.class))).thenReturn(true);
        boolean resultado = createTaskUseCase.createTask(randomId,
                "Design Homepage",
                5,
                "John Doe",
                TaskStatus.ACTIVE,
                fixedClock);
        Assertions.assertEquals(resultado, true);

    }

    @Test
    public void CreateTaskAlReadyExists() {
        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(taskOutPut);
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(true);
        Assertions.assertThrows(exception.TaskUseCaseException.class, () -> createTaskUseCase.createTask(randomId,
                "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));

    }

    @Test
    public void CreateTaskErrorSave() {
        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(taskOutPut);
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(false);
        when(taskOutPut.saveTask(any(Task.class))).thenReturn(false);
        Assertions.assertThrows(exception.TaskUseCaseException.class, () -> createTaskUseCase.createTask(randomId,
                "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));
    }

}
