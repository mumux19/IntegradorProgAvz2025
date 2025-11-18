package com.integrador.project.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.ProjectStatus;

import java.time.LocalDate;

@JsonIgnoreProperties
public class ProjectDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("status")
    private ProjectStatus status;
    @JsonProperty("description")
    private String description;
    public ProjectDTO() {}
    public ProjectDTO(String name, LocalDate endDate, LocalDate startDate, Long id, ProjectStatus status, String description) {
        this.name = name;
        this.endDate = endDate;
        this.startDate = startDate;
        this.id = id;
        this.status = status;
        this.description = description;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
