package input;

import model.Project;
import model.TaskStatus;

import java.time.Clock;
import java.time.LocalDateTime;

public interface CreateTaskInput {
    boolean createTask(Long id, String title,Integer estimateHours, String assignee, TaskStatus status, Clock clock);


}
