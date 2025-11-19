package usecase;

import exception.ProjectUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectOutPut;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteProjectUseCaseTest {

    @Mock
    ProjectOutPut projectOutPut;

    @Test
    public void deleteProjectTrue() throws Exception {
        Long id = 10L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut);

        when(projectOutPut.existsById(id)).thenReturn(true);
        when(projectOutPut.deleteProject(id)).thenReturn(true);

        boolean result = useCase.deleteProject(id);

        Assertions.assertTrue(result);
    }

    @Test
    public void deleteProjectNotExists() {
        Long id = 20L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut);

        when(projectOutPut.existsById(id)).thenReturn(false);

        Assertions.assertThrows(
                ProjectUseCaseException.class,
                () -> useCase.deleteProject(id)
        );
    }

    @Test
    public void deleteProjectFails() throws Exception {
        Long id = 30L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut);

        when(projectOutPut.existsById(id)).thenReturn(true);
        when(projectOutPut.deleteProject(id)).thenReturn(false);

        Assertions.assertThrows(
                ProjectUseCaseException.class,
                () -> useCase.deleteProject(id)
        );
    }
}
