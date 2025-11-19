package com.integrador.project.entity.data;


import jakarta.persistence.*;
import model.Project;
import model.ProjectStatus;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
@SequenceGenerator(
        name = "project_seq",
        sequenceName = "project_seq",
        allocationSize = 1
)
public class   ProjectData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @Column(name="id")
    private Long id;

    @Column( name="name",nullable = false)
    private String name;

    @Column(name="startDate",nullable = false)
    private LocalDate startDate;

    @Column(name="endDate",nullable = false)
    private LocalDate endDate;

    @Column(name="status",nullable = false)
    private ProjectStatus status;

    @Column(name="description",nullable = false)
    private String description;

    public ProjectData() {
    }


    public ProjectData(Long id, String name, LocalDate startDate, LocalDate endDate,
                       ProjectStatus status, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    public static ProjectData createProjectData(Project project) {

        return new ProjectData(
                project.getId(),
                project.getName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getDescription()
        );
    }


    public String getName() {
        return name;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}