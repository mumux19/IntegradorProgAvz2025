package output;

import model.Project;

public interface ProjectOutPut {
    boolean validateName(String name);

    boolean saveProject(Project project);

    boolean deleteProject(String name) throws Exception;
    Project findByName(String name);
}