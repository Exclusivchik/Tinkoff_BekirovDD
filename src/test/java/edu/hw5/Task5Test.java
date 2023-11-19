package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("test1")
    void test1() {
        String number = "А123ВЕ777";
        Assertions.assertTrue(Task5.isRussianCarNumber(number));
    }

    @Test
    @DisplayName("test2")
    void test2() {
        String number = "О777ОО177";
        Assertions.assertTrue(Task5.isRussianCarNumber(number));
    }

    @Test
    @DisplayName("test3")
    void test3() {
        String number = "123АВЕ777";
        Assertions.assertFalse(Task5.isRussianCarNumber(number));
    }

    @Test
    @DisplayName("test4")
    void test4() {
        String number = "А123ВГ77";
        Assertions.assertFalse(Task5.isRussianCarNumber(number));
    }

    @Test
    @DisplayName("test5")
    void test5() {
        String number = "А123ВЕ7777";
        Assertions.assertFalse(Task5.isRussianCarNumber(number));
    }
}
