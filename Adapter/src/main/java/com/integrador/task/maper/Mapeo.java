package com.integrador.task.maper;

import com.integrador.task.entity.TaskData;
import model.Task;

public class Mapeo {

    public static TaskData mapeoCoreData(TaskData taskData, ProjectData projectData) {

        return TaskData.create(taskData, projectData);
    }
}
