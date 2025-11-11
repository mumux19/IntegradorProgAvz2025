package usecase;

import exception.ProjectUseCaseException;
import input.DeleteProjectInput;
import output.ProjectOutPut;

public class DeleteProjectUseCase implements DeleteProjectInput {
    private ProjectOutPut projectOutPut;

    public  DeleteProjectUseCase(ProjectOutPut projectOutPut) {
        this.projectOutPut = projectOutPut;
    }

    @Override
    public boolean deleteProject(String name) throws Exception {
        if(!projectOutPut.validateName(name)){
            throw new ProjectUseCaseException("There is no project with that ID");
        }
        if(!projectOutPut.deleteProject(name)){
            throw new ProjectUseCaseException("Error deleting project");
        }
        return true;
    }


}
