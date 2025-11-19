package model;

import exception.TaskException;


import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private Integer estimateHours;
    private String assignee;
    private TaskStatus status;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;

    private Task(Long id, String title, Integer estimateHours, String assignee, TaskStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = createdAt;
        this.createdAt = createdAt;
    }

    public static Task create(Long id, String title, Integer estimateHours, String assignee, TaskStatus status, Clock clock) {
        LocalDateTime now = LocalDateTime.now();

        if (title == null || title.isEmpty()) {
            throw new TaskException("Task title cannot be null or empty");
        }
        if (estimateHours == null || estimateHours <= 0) {
            throw new TaskException("Estimate hours must be a positive integer");
        }
        if (assignee == null || assignee.isEmpty()) {
            throw new TaskException("Assignee cannot be null or empty");
        }
        if (status == null) {
            throw new TaskException("Task status cannot be null");
        }

        if (clock == null) {

            throw new TaskException("Clock cannot be null");
        }

        Instant nowSystem = Instant.now(Clock.systemDefaultZone());

        Instant nowFromClock = Instant.now(clock);

        if (nowFromClock.isAfter(nowSystem)) {
            throw new TaskException("Clock time cannot be in the future compared to system time");
        }


        return new Task(id, title, estimateHours, assignee, status, now);

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEstimateHours() {
        return estimateHours;
    }

    public String getAssignee() {
        return assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

