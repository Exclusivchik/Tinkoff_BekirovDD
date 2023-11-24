package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NginxLogTest {
    @Test
    void doesNotThrow() {
        String log = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        Assertions.assertDoesNotThrow(() -> new NginxLog(log, "somePath"));
    }

    @Test
    void throwsException() {
        String log = "IgorGoffman";
        Assertions.assertThrows(RuntimeException.class, () -> new NginxLog(log, "somePath"));
    }
}
