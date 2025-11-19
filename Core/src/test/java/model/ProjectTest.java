package model;

import exception.ProjectException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class ProjectTest {
    @Test
    public void factoryCreatesProjectSuccessfully() {
        Project project = Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Redesign the corporate website to improve user experience."
        );
        Assertions.assertNotNull(project);
    }
    @Test
    public void factoryThrowsExceptionForInvalidInputs() {

        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                null,
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Some description"
        ));
        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Some description"
        ));


        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "Website Redesign",
                null,
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Some description"
        ));
        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                null,
                ProjectStatus.ACTIVE,
                "Some description"
        ));


        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "Website Redesign",
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 10, 1),
                ProjectStatus.ACTIVE,
                "Some description"
        ));


        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                null,
                "Some description"
        ));


        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                null
        ));
        Assertions.assertThrows(ProjectException.class, () -> Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                ""
        ));
    }
}