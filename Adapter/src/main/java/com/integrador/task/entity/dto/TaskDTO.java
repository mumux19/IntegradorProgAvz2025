package com.integrador.task.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Project;
import model.TaskStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("project")
    private Project project;

    @JsonProperty("title")
    private String title;

    @JsonProperty("estimateHours")
    private Integer estimateHours;

    @JsonProperty("assignee")
    private String assignee;

    @JsonProperty("status")
    private TaskStatus status;

    public TaskDTO(){}

    public TaskDTO(Project project, String title, Integer estimateHours, String assignee, TaskStatus status) {
        this.project = project;
        this.title = title;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
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

}
