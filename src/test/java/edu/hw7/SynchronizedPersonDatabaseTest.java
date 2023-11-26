package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedPersonDatabaseTest {
    @Test
    public void testConcurrentAddAndFind() throws InterruptedException {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(2);

        executor.submit(() -> {
            database.add(new Person(1, "Alice", "123 Main St", "555-1234"));
            latch.countDown();
        });

        executor.submit(() -> {
            database.add(new Person(2, "Bob", "456 Elm St", "555-5678"));
            latch.countDown();
        });

        latch.await();

        Assertions.assertEquals(1, database.findByName("Alice").size());
        Assertions.assertEquals(1, database.findByAddress("123 Main St").size());
        Assertions.assertEquals(1, database.findByPhone("555-1234").size());
        Assertions.assertEquals(1, database.findByName("Bob").size());
        Assertions.assertEquals(1, database.findByAddress("456 Elm St").size());
        Assertions.assertEquals(1, database.findByPhone("555-5678").size());

        executor.shutdown();
    }

    @Test
    public void testConcurrentAddAndDelete() throws InterruptedException {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);

        database.add(new Person(1, "Alice", "123 Main St", "555-1234"));

        executor.submit(() -> {
            database.delete(1);
            latch.countDown();
        });

        latch.await();

        Assertions.assertEquals(0, database.findByName("Alice").size());
        Assertions.assertEquals(0, database.findByAddress("123 Main St").size());
        Assertions.assertEquals(0, database.findByPhone("555-1234").size());

        executor.shutdown();
    }
}
