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
    public boolean deleteProject(Long id) throws Exception {

        if (!projectOutPut.existsById(id)) {
            throw new ProjectUseCaseException("There is no project with that ID");
        }

        if (!projectOutPut.deleteProject(id)) {
            throw new ProjectUseCaseException("Error deleting project");
        }

        return true;
    }


}
