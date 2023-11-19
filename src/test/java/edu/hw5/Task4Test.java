package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("test1")
    void test1() {
        String password = ".*";
        Assertions.assertTrue(Task4.isSafePassword(password));
    }

    @Test
    @DisplayName("test2")
    void test2() {
        String password = "&!@&*!@#*(!IHSADJBHDAVUWD";
        Assertions.assertTrue(Task4.isSafePassword(password));
    }

    @Test
    @DisplayName("test3")
    void test3() {
        String password = "IgorGoffman78";
        Assertions.assertFalse(Task4.isSafePassword(password));
    }
}
