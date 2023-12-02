package edu.hw8;

import edu.hw8.Task3.PasswordBruteforcer;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordBruteforcerTest {
    private static final Map<String, String> map = Map.of(
        PasswordBruteforcer.getMD5("0000"), "igor",
        PasswordBruteforcer.getMD5("1488"), "mane",
        PasswordBruteforcer.getMD5("7878"), "dane",
        PasswordBruteforcer.getMD5("a56w"), "bobe"
    );

    @Test
    @DisplayName("boost")
    void boost() {
        LocalTime start1 = LocalTime.now();
        var result1 = PasswordBruteforcer.linearBruteforce(map, 4);
        Duration duration1 = Duration.between(start1, LocalTime.now());

        LocalTime start2 = LocalTime.now();
        var result2 = PasswordBruteforcer.multiThreadBruteforce(map, 4);
        Duration duration2 = Duration.between(start2, LocalTime.now());

        System.out.println(duration1.toMillis() / 1000.0 + " " + duration2.toMillis() / 1000.0);
        Assertions.assertEquals(result1, result2);
        assertThat(duration1).isGreaterThan(duration2);
    }

    @Test
    @DisplayName("all persons founded in linear algo")
    void test1() {
        var result1 = PasswordBruteforcer.linearBruteforce(map, 4);
        Assertions.assertEquals(4, result1.size());
    }

    @Test
    @DisplayName("all persons founded in multithread algo")
    void test2() {
        var result2 = PasswordBruteforcer.multiThreadBruteforce(map, 4);
        Assertions.assertEquals(4, result2.size());
    }
}
