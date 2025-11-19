package com.integrador.task.maper;

import com.integrador.task.entity.TaskData;
import model.Task;

public class Mapeo {

  public static Task taskMapperDataCore(TaskData taskData) {
    return Task.create(
        taskData.getTitle(),
        taskData.getEstimateHours(),
        taskData.getAssignee(),
        taskData.getStatus()
    );
  }
}
