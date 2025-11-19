package usecase;


import exception.ProjectUseCaseException;
import model.Project;
import model.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectOutPut;
import usecase.CreateProjectUseCase;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CreateProjectUseCaseTest {
    @Mock
    ProjectOutPut projectOutPut;
    @Test
    public void CreateProjectTrue() {
        CreateProjectUseCase createProjectUseCase=new CreateProjectUseCase(projectOutPut);

        when(projectOutPut.validateName("Website Redesign")).thenReturn(false);
        when(projectOutPut.saveProject(any(Project.class))).thenReturn(true);
        boolean resultado=createProjectUseCase.createProject( "Website Redesign",
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(2),
                ProjectStatus.ACTIVE,"Redesign the corporate website to improve user experience.");
        Assertions.assertEquals(resultado,true);

    }
    @Test
    public void CreateProjectAlreadyExists() {
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectOutPut);
        when(projectOutPut.validateName("Website Redesign")).thenReturn(true);
        Assertions.assertThrows(ProjectUseCaseException.class, () -> createProjectUseCase.createProject(
                "Website Redesign",
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(2),
                ProjectStatus.ACTIVE,
                "Redesign the corporate website to improve user experience."));


    }
    @Test
    public void CreateProjectErrorSave() {
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectOutPut);
        when(projectOutPut.validateName("Website Redesign")).thenReturn(false);
        when(projectOutPut.saveProject(any(Project.class))).thenReturn(false);
        Assertions.assertThrows(ProjectUseCaseException.class,  () -> createProjectUseCase.createProject(
                "Website Redesign",
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(2),
                ProjectStatus.ACTIVE,
                "Redesign the corporate website to improve user experience."));
    }


}