package input;

import model.ProjectStatus;
import model.TaskStatus;

import java.time.Clock;
import java.time.LocalDate;

public interface CreateTaskInput {
    boolean createTask(Long id, String title, Integer estimateHours, String assignee, TaskStatus status, Clock clock);

}
