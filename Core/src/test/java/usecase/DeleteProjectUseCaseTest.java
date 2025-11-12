package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import model.Project;
import model.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectOutPut;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DeleteProjectUseCaseTest {
    long randomId = ThreadLocalRandom.current().nextLong(1, 1000);
    @Mock
    ProjectOutPut projectOutPut;
    @Test
    public void DeleteProjectTrue() throws Exception {
        DeleteProjectUseCase deleteProjectUseCase=new DeleteProjectUseCase(projectOutPut);
        when(projectOutPut.validateName("Website Redesign")).thenReturn(true);
        when(projectOutPut.deleteProject("Website Redesign")).thenReturn(true);
        boolean resultado=deleteProjectUseCase.deleteProject("Website Redesign");
        Assertions.assertEquals(resultado,true);
    }
    @Test
    public void DeleteProjectNotExists() {
        DeleteProjectUseCase deleteProjectUseCase = new DeleteProjectUseCase(projectOutPut);
        when(projectOutPut.validateName("Website Redesign")).thenReturn(false);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> deleteProjectUseCase.deleteProject("Website Redesign"));

    }
    @Test
    public void DeleteProjectFalse() throws Exception {
        DeleteProjectUseCase deleteProjectUseCase = new DeleteProjectUseCase(projectOutPut);
        when(projectOutPut.validateName("Website Redesign")).thenReturn(true);
        when(projectOutPut.deleteProject("Website Redesign")).thenReturn(false);
        Assertions.assertThrows(BusinessRuleViolationException.class, () -> deleteProjectUseCase.deleteProject("Website Redesign"));
    }



}
