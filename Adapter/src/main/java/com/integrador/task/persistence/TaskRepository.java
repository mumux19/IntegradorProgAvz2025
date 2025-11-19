package com.integrador.task.persistence;

import com.integrador.task.crud.TaskCrud;
import com.integrador.task.entity.data.TaskData;
import com.integrador.task.mapper.TaskMapper;
import exception.TaskException;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import output.TaskOutPut;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TaskRepository implements TaskOutPut {

    private final TaskCrud taskCrud;

    @Autowired
    public TaskRepository(TaskCrud taskCrud) {
        this.taskCrud = taskCrud;
    }
    private Task mapToCore(TaskData data) {
        return TaskMapper.taskMapperDataCore(data,Clock.systemDefaultZone());
    }

    @Override
    public boolean saveTask(Task task) {
        if (task == null) throw new TaskException("Task cannot be null");

        TaskData data = TaskMapper.taskMapperCoreAData(task);
        taskCrud.save(data);
        return true;
    }

    @Override
    public List<Task> findTasks(Long projectId, Integer minEstimate, String assignee) {
        if (projectId == null) throw new TaskException("Project ID must be provided");

        List<TaskData> taskDataList = taskCrud.searchTasks(projectId, minEstimate, assignee);

        return taskDataList.stream()
                .map(this::mapToCore)
                .collect(Collectors.toList());
    }

    @Override
    public Task findTaskById(Long taskId) {
        if (taskId == null) throw new TaskException("Task ID must be provided");

        Optional<TaskData> optional = taskCrud.findById(taskId);
        TaskData data = optional.orElseThrow(() -> new TaskException("Task not found"));

        return mapToCore(data);
    }

    @Override
    public boolean deleteTaskById(Long taskId) {
        if (!taskCrud.existsById(taskId)) throw new TaskException("Task not found");
        taskCrud.deleteById(taskId);
        return true;
    }

    @Override
    public boolean validateTitle(String title) {
        if (title == null || title.isEmpty()) throw new TaskException("Title cannot be null or empty");
        return taskCrud.existsByTitle(title);
    }

    @Override
    public int countTasksByProjectId(Long projectId) {
        if (projectId == null) throw new TaskException("Project ID must be provided");
        return taskCrud.countByProjectId(projectId);
    }
}