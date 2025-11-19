package model;

import exception.TaskException;


import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class Task {

    private Long id;
    private Project project;
    private String title;
    private Integer estimateHours;
    private String assignee;
    private TaskStatus status;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;

    private Task(Long id, Project project, String title, Integer estimateHours, String assignee, TaskStatus status, LocalDateTime createdAt, LocalDateTime finishedAt) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt= finishedAt;
        this.createdAt = createdAt;
    }

    public Task() {
    }

    public static Task create(Project project,
                              String title,
                              Integer estimateHours,
                              String assignee,
                              TaskStatus status,Clock clock) {

        if (project == null) throw new TaskException("Task must belong to a project");
        if (title == null || title.isEmpty()) throw new TaskException("Task title cannot be null or empty");
        if (estimateHours == null || estimateHours <= 0) throw new TaskException("Estimate hours must be a positive integer");
        if (status == null) throw new TaskException("Task status cannot be null");
        if (clock == null) throw new TaskException("Clock cannot be null");

        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime finished = (status == TaskStatus.DONE) ? now : null;
        return new Task( null,project, title, estimateHours, assignee, status, now,finished);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public String getTitle() { return title; }

    public Integer getEstimateHours() { return estimateHours; }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public LocalDateTime getFinishedAt() { return finishedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}