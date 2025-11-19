package input;

import model.Task;

import java.util.List;

public interface FindTaskInput {
    List<Task> findTasks(Long projectId, Integer minEstimate, String assignee);
}