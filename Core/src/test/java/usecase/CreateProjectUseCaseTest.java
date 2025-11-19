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

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProjectUseCaseTest {

    @Mock
    ProjectOutPut projectOutPut;

    @Test
    public void createProjectSuccess() {
        Project project = Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign corporate website"
        );

        when(projectOutPut.existsByName(project.getName())).thenReturn(false);
        when(projectOutPut.save(project)).thenReturn(true);

        CreateProjectUseCase useCase = new CreateProjectUseCase(projectOutPut);

        Project result = useCase.createProject(project);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(project.getName(), result.getName());
    }

    @Test
    public void createProjectNameExists() {
        Project project = Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign corporate website"
        );

        when(projectOutPut.existsByName(project.getName())).thenReturn(true);

        CreateProjectUseCase useCase = new CreateProjectUseCase(projectOutPut);

        Assertions.assertThrows(ProjectUseCaseException.class, () -> useCase.createProject(project));
    }

    @Test
    public void createProjectSaveFails() {
        Project project = Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign corporate website"
        );

        when(projectOutPut.existsByName(project.getName())).thenReturn(false);
        when(projectOutPut.save(project)).thenReturn(false);

        CreateProjectUseCase useCase = new CreateProjectUseCase(projectOutPut);

        Assertions.assertThrows(ProjectUseCaseException.class, () -> useCase.createProject(project));
    }
}
