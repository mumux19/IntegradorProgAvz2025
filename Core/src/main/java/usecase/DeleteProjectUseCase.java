package usecase;

import exception.ProjectUseCaseException;
import input.DeleteProjectInput;
import output.ProjectOutPut;
import output.TaskOutPut;

public class DeleteProjectUseCase implements DeleteProjectInput {
    private final ProjectOutPut projectOutPut;
    private final TaskOutPut taskOutPut;

    public DeleteProjectUseCase(ProjectOutPut projectOutPut, TaskOutPut taskOutPut) {
        this.projectOutPut = projectOutPut;
        this.taskOutPut = taskOutPut;
    }

    @Override
    public boolean deleteProject(Long projectId) {
        if (!projectOutPut.existsById(projectId))
            throw new ProjectUseCaseException("Project not found");

        if (taskOutPut.countTasksByProjectId(projectId) > 0)
            throw new ProjectUseCaseException("Cannot delete project with tasks");

        return projectOutPut.deleteById(projectId);
    }
}