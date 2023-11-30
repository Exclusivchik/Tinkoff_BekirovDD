package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SynchronizedPersonDatabaseTest {
    @Test
    public void testConcurrentAddAndFind() throws InterruptedException {
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();
        Person igorGoffman = new Person(78, "Igor", "78street", "+787878");
        Thread thread1 = new Thread(() -> {
            database.add(igorGoffman);
            database.delete(78);
        });
        Thread thread2 = new Thread(() -> {
            var param1 = database.findByName("Igor");
            var param2 = database.findByAddress("78street");
            assertThat(param1.size() + param2.size()).isIn(List.of(0, 2));
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
