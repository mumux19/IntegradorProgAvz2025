package com.integrador.task.mapper;

import com.integrador.project.mapper.ProjectMapper;
import com.integrador.task.entity.data.TaskData;
import model.Project;
import model.Task;

import java.time.Clock;

public class TaskMapper {

    public static Task taskMapperDataCore(TaskData data, Clock clock) {
        if (data == null) return null;
        Project projectCore = ProjectMapper.projectMapperDataCore(data.getProject());

        return Task.create(
                projectCore,
                data.getTitle(),
                data.getEstimateHours(),
                data.getAssignee(),
                data.getStatus(),
                clock
        );
    }


    public static TaskData taskMapperCoreAData(Task task) {
        return TaskData.createTaskData(task);
    }


}