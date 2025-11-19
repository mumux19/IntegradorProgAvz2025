package output;

import model.Project;

import java.util.Optional;

public interface ProjectOutPut {
    boolean save(Project project);
    Optional<Project> findById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean deleteById(Long id);
}