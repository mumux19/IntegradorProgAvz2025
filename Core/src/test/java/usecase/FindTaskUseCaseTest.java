package usecase;

import input.FindTaskInput;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.TaskOutPut;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class FindTaskUseCaseTest {

    @Mock
    TaskOutPut taskOutPut;

    long randomId = ThreadLocalRandom.current().nextLong(1, 1000);
    Clock fixedClock = Clock.fixed(
            LocalDateTime.of(2025, 11, 11, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );


    @Test
    public void FindTaskByTitleExists() throws Exception {

        FindTaskUseCase findTaskUseCase = new FindTaskUseCase(taskOutPut);


        Task task = Task.create(randomId, "Design Homepage", 5, "John Doe", TaskStatus.ACTIVE, fixedClock);

        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(true);

        when(taskOutPut.findTask("Design Homepage")).thenReturn(task);
        Task resultado = findTaskUseCase.findTask("Design Homepage");

        Assertions.assertEquals(resultado, task);
    }


    @Test
    public void FindTaskByTitleNotExists() {
        FindTaskUseCase findTaskUseCase = new FindTaskUseCase(taskOutPut);

        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(false);
        Assertions.assertThrows(exception.TaskUseCaseException.class, () -> findTaskUseCase.findTask("Design Homepage"));


    }

    @Test
    public void FindTaskByTitleNull() {

        FindTaskUseCase findTaskUseCase = new FindTaskUseCase(taskOutPut);

        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(true);

        when(taskOutPut.findTask("Design Homepage")).thenReturn(null);
        Assertions.assertThrows(exception.TaskUseCaseException.class, () -> findTaskUseCase.findTask("Design Homepage"));
    }
}
