package com.integrador.project.persistence;

import com.integrador.project.crud.ProjectCrud;
import com.integrador.project.entity.data.ProjectData;
import com.integrador.project.mapper.ProjectMapper;
import exception.ProjectException;
import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import output.ProjectOutPut;
import output.TaskOutPut;

import java.util.Optional;

@Repository
public class ProjectRepository implements ProjectOutPut {

    private final ProjectCrud projectCrud;
    private final TaskOutPut taskOutPut;

    @Autowired
    public ProjectRepository(ProjectCrud projectCrud, TaskOutPut taskOutPut) {
        this.projectCrud = projectCrud;
        this.taskOutPut = taskOutPut;
    }

    @Override
    public boolean save(Project project) {
        if (project == null) {
            throw new ProjectException("Project cannot be null");
        }
        ProjectData data = ProjectMapper.projectMapperCoreAData(project);
        projectCrud.save(data);
        return true;
    }

    @Override
    public Optional<Project> findById(Long id) {
        if (id == null) throw new ProjectException("Project ID must be provided");

        Optional<ProjectData> optional = projectCrud.findById(id);
        return optional.map(ProjectMapper::projectMapperDataCore);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) throw new ProjectException("Project ID must be provided");
        return projectCrud.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new ProjectException("Project name must be provided");
        }
        return projectCrud.existsByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null) throw new ProjectException("Project ID must be provided");

        if (!projectCrud.existsById(id)) {
            throw new ProjectException("Project not found");
        }

        if (taskOutPut.countTasksByProjectId(id) > 0) {
            throw new ProjectException("Cannot delete project: it still has tasks");
        }

        projectCrud.deleteById(id);
        return true;
    }
}