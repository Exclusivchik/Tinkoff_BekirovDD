package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.FibCalculator;
import edu.hw10.Task2.MulClass;
import edu.hw10.Task2.MyFibCalculator;
import edu.hw10.Task2.MyMul;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CacheProxyTest {
    private static final Path DIRECTORY = Path.of("src/main/java/edu/hw10/Task2/cache");

    @BeforeAll
    static void before() throws IOException {
        Files.createDirectory(DIRECTORY);
    }

    @AfterAll
    static void after() throws IOException {
        try (var files = Files.list(DIRECTORY)) {
            files.forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Files.deleteIfExists(DIRECTORY);
    }

    @Test
    @DisplayName("Создаем файл")
    void test1() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            filesCount = pathStream.count();
        }
        FibCalculator fibCalculator = new MyFibCalculator();
        FibCalculator proxyFib = CacheProxy.create(fibCalculator, FibCalculator.class);
        long result = proxyFib.fib(5);
        long newFilesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            newFilesCount = pathStream.count();
        }
        Assertions.assertTrue(result == 8 && newFilesCount == filesCount + 1);
    }

    @Test
    @DisplayName("Не создаем файл")
    void test2() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            filesCount = pathStream.count();
        }
        FibCalculator fibCalculator = new MyFibCalculator();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);

        long result = proxy.fib(5);
        long newFilesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            newFilesCount = pathStream.count();
        }

        Assertions.assertTrue(result == 8 && newFilesCount == filesCount);
    }

    @Test
    @DisplayName("Не создаем файл потому что persist = false")
    void test3() throws IOException {
        long filesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            filesCount = pathStream.count();
        }
        MulClass myMul = new MyMul();
        MulClass proxy = CacheProxy.create(myMul, MulClass.class);

        long result = proxy.mul(39, 2);
        long newFilesCount;
        try (var pathStream = Files.list(DIRECTORY)) {
            newFilesCount = pathStream.count();
        }

        Assertions.assertTrue(result == 78 && newFilesCount == filesCount);
    }
}
