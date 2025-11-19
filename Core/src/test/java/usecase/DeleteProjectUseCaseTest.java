package usecase;

import exception.ProjectUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectOutPut;
import output.TaskOutPut;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteProjectUseCaseTest {

    @Mock
    ProjectOutPut projectOutPut;

    @Mock
    TaskOutPut taskOutPut;

    @Test
    public void deleteProjectSuccess() {
        Long projectId = 10L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut, taskOutPut);

        when(projectOutPut.existsById(projectId)).thenReturn(true);
        when(taskOutPut.countTasksByProjectId(projectId)).thenReturn(0);
        when(projectOutPut.deleteById(projectId)).thenReturn(true);

        boolean result = useCase.deleteProject(projectId);

        Assertions.assertTrue(result);
    }

    @Test
    public void deleteProjectNotFound() {
        Long projectId = 20L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut, taskOutPut);

        when(projectOutPut.existsById(projectId)).thenReturn(false);

        Assertions.assertThrows(
                ProjectUseCaseException.class,
                () -> useCase.deleteProject(projectId)
        );
    }

    @Test
    public void deleteProjectWithTasksFails() {
        Long projectId = 30L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut, taskOutPut);

        when(projectOutPut.existsById(projectId)).thenReturn(true);
        when(taskOutPut.countTasksByProjectId(projectId)).thenReturn(5);

        Assertions.assertThrows(
                ProjectUseCaseException.class,
                () -> useCase.deleteProject(projectId)
        );
    }

    @Test
    public void deleteProjectDeleteFails() {
        Long projectId = 40L;

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(projectOutPut, taskOutPut);

        when(projectOutPut.existsById(projectId)).thenReturn(true);
        when(taskOutPut.countTasksByProjectId(projectId)).thenReturn(0);
        when(projectOutPut.deleteById(projectId)).thenReturn(false);

        Assertions.assertThrows(
                ProjectUseCaseException.class,
                () -> useCase.deleteProject(projectId)
        );
    }
}
