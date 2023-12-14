package edu.hw9;

import edu.hw9.Task1.CollectedData;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    @Test
    @DisplayName("Работа коллектора")
    void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        StatsCollector statsCollector = new StatsCollector();
        Runnable task = () -> {
            statsCollector.push("sum", new double[]{1, 2, 3, 4, 5});
            statsCollector.push("average", new double[]{0, 1, 2, 3});
            statsCollector.push("min", new double[]{1, 2, 3, 4});
            statsCollector.push("max", new double[]{1, 2, 3, 4});
        };
        for (int i = 0; i < 2; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();
        executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        statsCollector.close();
        var result = statsCollector.stats();
        assertThat(result).containsExactlyInAnyOrder(
            new CollectedData("sum", 15),
            new CollectedData("average", 1.5),
            new CollectedData("min", 1),
            new CollectedData("max", 4),
            new CollectedData("sum", 15),
            new CollectedData("average", 1.5),
            new CollectedData("min", 1),
            new CollectedData("max", 4)
        );
    }
}
