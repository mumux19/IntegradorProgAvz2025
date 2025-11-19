package output;

import model.Project;

public interface ProjectOutPut {
    boolean validateName(String name);
    boolean saveProject(Project project);
    boolean deleteProject(Long id) throws Exception;

    Project findById(Long id);

    boolean existsById(Long id);
}