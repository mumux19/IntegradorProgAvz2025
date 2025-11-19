package usecase;


import exception.ProjectUseCaseException;
import input.CreateProjectInput;
import model.Project;
import model.ProjectStatus;
import output.ProjectOutPut;

import java.time.LocalDate;

public class CreateProjectUseCase implements CreateProjectInput {
    private final ProjectOutPut projectOutPut;

    public CreateProjectUseCase(ProjectOutPut projectOutPut) {
        this.projectOutPut = projectOutPut;
    }



    @Override
    public Project createProject(Project project) {
        if (projectOutPut.existsByName(project.getName())) {
            throw new ProjectUseCaseException("Project name already exists");
        }
        if (!projectOutPut.save(project)) {
            throw new ProjectUseCaseException("Failed to save project");
        }
        return project;
    }


}