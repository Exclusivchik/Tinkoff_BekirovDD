package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixedThreadPoolTest {
    long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
    @Test
    @DisplayName("boost")
    void boost() {
        int threadsCount = 40;
        ThreadPool threadPool = new FixedThreadPool(threadsCount);
        LocalTime start1 = LocalTime.now();
        for (int i = 0; i < threadsCount; i++) {
            int n = i;
            threadPool.execute(() -> fib(n));
        }
        threadPool.start();
        LocalTime end1 = LocalTime.now();
        Duration duration1 = Duration.between(start1, end1);

        LocalTime start2 = LocalTime.now();
        for (int i = 0; i < threadsCount; i++) {
            fib(i);
        }
        LocalTime end2 = LocalTime.now();
        Duration duration2 = Duration.between(start2, end2);
        System.out.println(duration1.toMillis() / 1000.0 + " " + duration2.toMillis() / 1000.0);
        assertThat(duration2).isGreaterThan(duration1);
    }
}
