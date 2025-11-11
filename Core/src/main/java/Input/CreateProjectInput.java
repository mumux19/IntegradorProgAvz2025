package input;

import exception.ProjectUseCaseException;
import model.Project;
import model.ProjectStatus;

import java.time.LocalDate;

public interface CreateProjectInput {
    boolean createProject(long id, String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, String description) throws ProjectUseCaseException;


}