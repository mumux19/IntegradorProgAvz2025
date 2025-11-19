package com.integrador.project.mapper;

import com.integrador.project.entity.data.ProjectData;
import model.Project;

public class ProjectMapper {
    public static Project projectMapperDataCore (ProjectData project) {
        return Project.create(
                project.getName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getDescription()
        );
    }

    public static ProjectData projectMapperCoreAData (Project project) {
        return ProjectData.createProjectData(project);

    }
}
