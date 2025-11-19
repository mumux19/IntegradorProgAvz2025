package com.integrador.project.mapper;

import com.integrador.project.entity.data.ProjectData;
import model.Project;

public class ProjectMapper {


    public static Project projectMapperDataCore(ProjectData data) {
        if (data == null) return null;
        return Project.create(
                data.getName(),
                data.getStartDate(),
                data.getEndDate(),
                data.getStatus(),
                data.getDescription()
        );
    }

    public static ProjectData projectMapperCoreAData(Project project) {
        return ProjectData.createProjectData(project);
    }


}

