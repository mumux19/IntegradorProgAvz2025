package model;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

public class TaskTest {
    long randomId = ThreadLocalRandom.current().nextLong(1, 1000);
    Clock fixedClock = Clock.fixed(
            LocalDateTime.of(2025, 11, 11, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );
    @Test
    public void FactoryTrue() {
        Task task= Task.create(randomId,
                "Website Redesign",
                5,
                "John Doe",
                TaskStatus.ACTIVE,
                fixedClock);

        Assertions.assertNotNull(task);

    }


    @Test
    public void FactoryFalse() {
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(null, "Website Redesign", 5, "John Doe", TaskStatus.ACTIVE, java.time.Clock.systemUTC());
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId, null, 5, "John Doe",
                    TaskStatus.ACTIVE,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "",
                    5,
                    "John Doe",
                    TaskStatus.ACTIVE,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    -1,
                    "John Doe",
                    TaskStatus.ACTIVE,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    null,
                    "John Doe",
                    TaskStatus.ACTIVE,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    5,
                    null,
                    TaskStatus.ACTIVE,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    5,
                    "",
                    TaskStatus.ACTIVE,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    5,
                    "John Doe",
                    null,
                    fixedClock);
        });
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    5,
                    "John Doe",
                    TaskStatus.ACTIVE,
                    null);
        });

        LocalDateTime futureTime = LocalDateTime.of(2030, 1, 1, 0, 0);
        Clock futureClock = Clock.fixed(futureTime.toInstant(ZoneOffset.UTC),ZoneOffset.UTC);
        Assertions.assertThrows(exception.TaskException.class, () -> {
            Task.create(randomId,
                    "Website Redesign",
                    5,
                    "John Doe",
                    TaskStatus.ACTIVE,
                    futureClock);
        });
    }
}
