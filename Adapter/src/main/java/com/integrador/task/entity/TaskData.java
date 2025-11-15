package com.integrador.task.entity;


import jakarta.persistence.*;
import model.Task;
import model.TaskStatus;

import java.time.LocalDateTime;

@Entity(name = "tasks")
@SequenceGenerator(
        name = "tasks_id_seq",
        sequenceName = "tasks_id_seq",
        initialValue = 1,
        allocationSize = 1)
public class TaskData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectData project;

    @Column(name = "title")
    private String title;

    @Column(name = "estimate_hours")
    private Integer estimateHours;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TaskData() {
    }

    public TaskData(Long id, ProjectData project, String title, Integer estimateHours, String assignee,
                    TaskStatus status, LocalDateTime finishedAt, LocalDateTime createdAt) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
    }

    // Factory para convertir de dominio a JPA
    public static TaskData create(Task task, ProjectData projectData) {
        return new TaskData(
                task.getId(),
                projectData,
                task.getTitle(),
                task.getEstimateHours(),
                task.getAssignee(),
                task.getStatus(),
                task.getFinishedAt(),
                task.getCreatedAt()
        );
    }


    // Getters y setters
    public Long getId() {
        return id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEstimateHours() {
        return estimateHours;
    }

    public void setEstimateHours(Integer estimateHours) {
        this.estimateHours = estimateHours;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}