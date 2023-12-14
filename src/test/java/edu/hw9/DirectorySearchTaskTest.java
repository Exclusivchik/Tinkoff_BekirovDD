package edu.hw9;

import edu.hw9.Task2.DirectorySearchTask;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearchTaskTest {
    @Test
    @DisplayName("Работоспособность")
    void test1() throws InterruptedException {
        DirectorySearchTask directorySearchTask = new DirectorySearchTask(new File("src/test/java/edu/hw9/for_test_task2"), 2);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        var response = forkJoinPool.invoke(directorySearchTask);
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(2000, TimeUnit.MILLISECONDS);
        forkJoinPool.close();
        assertThat(response).containsExactlyInAnyOrder(
            new File("src/test/java/edu/hw9/for_test_task2/1/vb"),
            new File("src/test/java/edu/hw9/for_test_task2/2")
        );
    }
}
