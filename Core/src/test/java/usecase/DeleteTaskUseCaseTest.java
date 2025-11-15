package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.TaskOutPut;
import usecase.DeleteTaskUseCase;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskUseCaseTest {

    @Mock
    TaskOutPut taskOutPut;

    @Test
    public void DeleteTaskTrue() throws Exception {
        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(true);
        when(taskOutPut.deleteTask("Design Homepage")).thenReturn(true);
        boolean resultado = deleteTaskUseCase.deleteTask("Design Homepage");

        Assertions.assertEquals(resultado, true);

    }

    @Test
    public void DeleteTaskNotExists() {

        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(false);
        Assertions.assertThrows(exception.TaskUseCaseException.class, () -> deleteTaskUseCase.deleteTask("Design Homepage"));


    }

    @Test
    public void DeleteTaskFalse() throws Exception {
        DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(taskOutPut);
        when(taskOutPut.validateTitle("Design Homepage")).thenReturn(true);
        when(taskOutPut.deleteTask("Design Homepage")).thenReturn(false);
        Assertions.assertThrows(exception.TaskUseCaseException.class, () -> deleteTaskUseCase.deleteTask("Design Homepage"));
    }


}
