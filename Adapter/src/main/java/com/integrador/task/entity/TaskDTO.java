package com.integrador.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.integrador.project.entity.data.ProjectData;
import jakarta.persistence.*;
import model.TaskStatus;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("project")
    private ProjectData project;

    @JsonProperty("title")
    private String title;

    @JsonProperty("estimate_hours")
    private Integer estimateHours;

    @JsonProperty("assignee")
    private String assignee;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public TaskDTO() {
    }

    private TaskDTO(Long id, ProjectData project, String title, Integer estimateHours, String assignee,
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

    public Long getId() {
        return id;
    }

    public ProjectData getProject() {
        return project;
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
