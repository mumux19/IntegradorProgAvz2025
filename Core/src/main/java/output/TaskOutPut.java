package output;

import model.Task;

import java.util.List;

public interface TaskOutPut {

    boolean saveTask(Task task);
    List<Task> findTasks(Long projectId, Integer minEstimate, String assignee);
    Task findTaskById(Long taskId);
    boolean deleteTaskById(Long taskId);
    boolean validateTitle(String title);
    int countTasksByProjectId(Long projectId);
}