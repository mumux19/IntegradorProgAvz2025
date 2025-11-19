package model;

import exception.TaskException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskTest {

    Clock fixedClock = Clock.fixed(
            LocalDateTime.of(2025, 11, 11, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );

    @Test
    public void FactoryTrue() {
        Project project = Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Corporate website redesign"
        );

        Task task = Task.create(
                project,
                "Design landing page",
                5,
                "John Doe",
                TaskStatus.ACTIVE,
                fixedClock
        );

        Assertions.assertNotNull(task);
        Assertions.assertEquals(project, task.getProject());
        Assertions.assertEquals("Design landing page", task.getTitle());
        Assertions.assertEquals(5, task.getEstimateHours());
        Assertions.assertEquals("John Doe", task.getAssignee());
        Assertions.assertEquals(TaskStatus.ACTIVE, task.getStatus());
        Assertions.assertNotNull(task.getCreatedAt());
        Assertions.assertNull(task.getFinishedAt());
    }

    @Test
    public void FactoryFalse() {
        Project project = Project.create(
                "Website Redesign",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 12, 1),
                ProjectStatus.ACTIVE,
                "Corporate website redesign"
        );

        Assertions.assertThrows(TaskException.class, () ->
                Task.create(null, "Task Title", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));

        Assertions.assertThrows(TaskException.class, () ->
                Task.create(project, null, 5, "John Doe", TaskStatus.ACTIVE, fixedClock));

        Assertions.assertThrows(TaskException.class, () ->
                Task.create(project, "", 5, "John Doe", TaskStatus.ACTIVE, fixedClock));

        Assertions.assertThrows(TaskException.class, () ->
                Task.create(project, "Task Title", -1, "John Doe", TaskStatus.ACTIVE, fixedClock));

        Assertions.assertThrows(TaskException.class, () ->
                Task.create(project, "Task Title", 5, "John Doe", null, fixedClock));

        Assertions.assertThrows(TaskException.class, () ->
                Task.create(project, "Task Title", 5, "John Doe", TaskStatus.ACTIVE, null));
    }
}