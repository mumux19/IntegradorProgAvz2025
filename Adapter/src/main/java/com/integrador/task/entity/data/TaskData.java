package com.integrador.task.entity.data;


import com.integrador.project.entity.data.ProjectData;
import jakarta.persistence.*;
import model.Task;
import model.TaskStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@SequenceGenerator(
        name = "task_seq",
        sequenceName = "task_seq",
        allocationSize = 1
)
public class TaskData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project", nullable = false)
    private ProjectData project;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="estimateHours",nullable = false)
    private Integer estimateHours;

    @Column(name="assignee",nullable = false)
    private String assignee;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private TaskStatus status;

    @Column(name="finishedAt",nullable = false)
    private LocalDateTime finishedAt;

    @Column(name="createAt",nullable = false)
    private LocalDateTime createdAt;


    public TaskData() {}

    public TaskData(Long id, ProjectData project, String title, Integer estimateHours,
                    String assignee, TaskStatus status, LocalDateTime finishedAt, LocalDateTime createdAt) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
    }

    public static TaskData fromDomain(Task task) {
        return new TaskData(
                task.getId(),
                ProjectData.createProjectData(task.getProject()),
                task.getTitle(),
                task.getEstimateHours(),
                task.getAssignee(),
                task.getStatus(),
                task.getFinishedAt(),
                task.getCreatedAt()
        );
    }

    public static TaskData createTaskData(Task task) {
        return fromDomain(task);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectData getProject() {
        return project;
    }

    public void setProject(ProjectData project) {
        this.project = project;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

}