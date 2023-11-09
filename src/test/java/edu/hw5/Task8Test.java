package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task8Test {
    @Test
    @DisplayName("test1")
    void test1() {
        Assertions.assertTrue(Task8.isOddLength("000"));
    }

    @Test
    @DisplayName("test2")
    void test2() {
        Assertions.assertFalse(Task8.isOddLength("00"));
    }

    @Test
    @DisplayName("test3")
    void test3() {
        Assertions.assertFalse(Task8.isOddLength("IGORGOFFMAN"));
    }

    @Test
    @DisplayName("test4")
    void test4() {
        Assertions.assertTrue(Task8.startWith0AndOddLengthOrStartWith1AndEvenLength("010"));
    }

    @Test
    @DisplayName("test5")
    void test5() {
        Assertions.assertTrue(Task8.startWith0AndOddLengthOrStartWith1AndEvenLength("100010"));
    }

    @Test
    @DisplayName("test6")
    void test6() {
        Assertions.assertFalse(Task8.startWith0AndOddLengthOrStartWith1AndEvenLength("IGORGOFFMAN"));
    }

    @Test
    @DisplayName("test7")
    void test7() {
        Assertions.assertTrue(Task8.zeroCountDividesBy3("10001010101111"));
    }

    @Test
    @DisplayName("test8")
    void test8() {
        Assertions.assertFalse(Task8.zeroCountDividesBy3("10011001110"));
    }

    @Test
    @DisplayName("test9")
    void test9() {
        Assertions.assertTrue(Task8.zeroCountDividesBy3(""));
    }

    @Test
    @DisplayName("test10")
    void test10() {
        Assertions.assertFalse(Task8.zeroCountDividesBy3("IGORGOFFMAN"));
    }
}
