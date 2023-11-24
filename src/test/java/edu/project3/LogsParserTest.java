package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogsParserTest {
    @Test
    void doesNotThrow() {
        String path = "out/artifacts/nginx_log_stats_jar/logs_for_project3/log1.log";
        Assertions.assertTrue(Files.exists(Path.of(path)));
        Assertions.assertDoesNotThrow(() -> LogsParser.parseFromFile(path, null, null));
    }

    @Test
    void throwException() {
        String path = "out/artifacts/nginx_log_stats_jar/logs_for_project3/IgorGoffman.log";
        Assertions.assertThrows(RuntimeException.class, () -> LogsParser.parseFromFile(path, null, null));
    }

    @Test
    void requiredSize() {
        String path = "out/artifacts/nginx_log_stats_jar/logs_for_project3/log1.log";
        var logs = LogsParser.parseFromFile(path, null, null);
        Assertions.assertEquals(11, logs.size());
    }
}
