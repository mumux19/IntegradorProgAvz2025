package model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class ProjectTest {
    long randomId = ThreadLocalRandom.current().nextLong(1, 1000);
@Test
    public void FactoryTrue() {

    Project project = Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience."

    );
    Assertions.assertNotNull(project);
}
@Test
    public void FacoryFalse(){
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(null,
            "Website Redesign",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            null,
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            null,
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2025, 10, 1),
            null,
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2025, 12, 1),
            LocalDate.of(2025, 10, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2020, 10, 1),
            LocalDate.of(2020, 12, 1),
            ProjectStatus.ACTIVE,
            "Redesign the corporate website to improve user experience.");});

    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            null,
            "Redesign the corporate website to improve user experience.");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            "");});
    Assertions.assertThrows(ProjectException.class, () -> {Project.create(randomId,
            "Website Redesign",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2025, 12, 1),
            ProjectStatus.ACTIVE,
            null);});

}
}
