package model;
import exception.ValidationException;


import java.time.LocalDate;

public class Project {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private String description;
    private Project(Long id, String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }
    public static Project create(Long id, String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, String description) {
       if (id == null) {
           throw new ValidationException("Project ID cannot be null");
       }
       if (name == null || name.isEmpty()) {
           throw new ValidationException("Project name cannot be null or empty");
         }
       if (startDate == null) {
           throw new ValidationException("Start date cannot be null");
       }
       if (endDate == null) {
           throw new ValidationException("End date cannot be null");
       }
        if (endDate.isBefore(startDate)) {
            throw new ValidationException("The end date cannot be earlier than the start date");
        }
        if (endDate.isBefore(LocalDate.now())) {
            throw new ValidationException("The end date cannot be earlier than today");
        }
       if (status == null) {
           throw new ValidationException("Project status cannot be null");
       }
       if (description == null || description.isEmpty()) {
           throw new ValidationException("Project description cannot be null or empty");
       }

        return new Project(id, name, startDate, endDate, status, description);
    }



}
