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
    public boolean createProject(long id, String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, String description)  {
        Project project=Project.create(id,name,startDate,endDate,status,description);
        if (projectOutPut.validateName(name)){
            throw new ProjectUseCaseException("The project already exists");
        }

        if(projectOutPut.saveProject(project)){
            return true;
        }

        throw new ProjectUseCaseException("Error saving project");
    }
}
