package com.integrador.project.persistence;

import com.integrador.project.crud.ProjectCrud;
import com.integrador.project.entity.data.ProjectData;
import com.integrador.project.mapper.ProjectMapper;
import exception.ProjectException;
import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import output.ProjectOutPut;

@Repository
public class ProjectRepository implements ProjectOutPut {

    private final ProjectCrud projectCrud;

    @Autowired
    public ProjectRepository(ProjectCrud projectCrud) {
        this.projectCrud = projectCrud;
    }

    @Override
    public boolean validateName(String name) {
        return projectCrud.existsByName(name);
    }

    @Override
    public boolean saveProject(Project project) {
        ProjectData data = ProjectMapper.projectMapperCoreAData(project);
        projectCrud.save(data);
        return true;
    }

    @Override
    public boolean deleteProject(Long projectId) throws Exception {
        if (!projectCrud.existsById(projectId)) {
            throw new ProjectException("There is no project with that ID");
        }


        projectCrud.deleteById(projectId);
        return true;


    }
    @Override
    public Project findById(Long id) {
        ProjectData data = projectCrud.findById(id)
                .orElseThrow(() -> new ProjectException("Project not found"));

        return ProjectMapper.projectMapperDataCore(data);
    }

    @Override
    public boolean existsById(Long id) {
        return projectCrud.existsById(id);
    }
}