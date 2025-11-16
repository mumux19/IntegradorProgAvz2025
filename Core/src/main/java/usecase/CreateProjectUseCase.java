package usecase;


import exception.ProjectUseCaseException;
import input.CreateProjectInput;
import model.Project;
import model.ProjectStatus;
import output.ProjectOutPut;

import java.time.LocalDate;

public class CreateProjectUseCase implements CreateProjectInput {
    private ProjectOutPut projectOutPut;

    public CreateProjectUseCase(ProjectOutPut projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    @Override
    public Project createProject(String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, String description) {
        Project project = Project.create(randomId, name, startDate, endDate, status, description);
        if (projectOutPut.validateName(name)) {
            throw new ProjectUseCaseException("The project already exists");
        }

        if (projectOutPut.saveProject(project)) {
            return project;
        }

        throw new ProjectUseCaseException("Error saving project");
    }
}
