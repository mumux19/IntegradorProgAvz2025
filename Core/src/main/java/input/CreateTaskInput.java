package input;

import model.Project;
import model.TaskStatus;

import java.time.Clock;


public interface CreateTaskInput {
    boolean createTask(Project project, String title, Integer estimateHours,
                       String assignee, TaskStatus status, Clock clock);
}