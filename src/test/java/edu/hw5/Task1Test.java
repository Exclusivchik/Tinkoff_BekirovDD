package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;

public class Task1Test {
    @Test
    @DisplayName("test1")
    void test1() {
        List<String> sessions = List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20");

        var meanDuration = Task1.getDuration(sessions);
        long required = (3 * 60 + 40) * 60;
        Assertions.assertEquals(meanDuration, Duration.ofSeconds(required));
    }

    @Test
    @DisplayName("test2")
    void test2() {
        List<String> sessions = List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 2022-04-02, 01:20");

        var meanDuration = Task1.getDuration(sessions);
        long required = (3 * 60 + 30) * 60;
        Assertions.assertEquals(meanDuration, Duration.ofSeconds(required));
    }

    @Test
    @DisplayName("test3")
    void test3() {
        List<String> sessions = List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 67:20");

        System.out.println();
        Assertions.assertThrows(RuntimeException.class, () -> Task1.getDuration(sessions));
    }
}
