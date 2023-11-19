package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    @DisplayName("test1")
    void test1() {
        String string = "0100000000";
        Assertions.assertTrue(Task7.containsMoreOrEqual3SymbolsAndTheThirdIs0(string));
    }

    @Test
    @DisplayName("test2")
    void test2() {
        String string = "101000000";
        Assertions.assertFalse(Task7.containsMoreOrEqual3SymbolsAndTheThirdIs0(string));
    }

    @Test
    @DisplayName("test3")
    void test3() {
        String string = "IgorGoffman";
        Assertions.assertFalse(Task7.containsMoreOrEqual3SymbolsAndTheThirdIs0(string));
    }

    @Test
    @DisplayName("test4")
    void test4() {
        String string = "00100100";
        Assertions.assertTrue(Task7.startAndEndWithTheSameSymbol(string));
    }

    @Test
    @DisplayName("test5")
    void test5() {
        String string = "0";
        Assertions.assertTrue(Task7.startAndEndWithTheSameSymbol(string));
    }

    @Test
    @DisplayName("test6")
    void test6() {
        String string = "01";
        Assertions.assertFalse(Task7.startAndEndWithTheSameSymbol(string));
    }

    @Test
    @DisplayName("test7")
    void test7() {
        String string = "IgorGofmann";
        Assertions.assertFalse(Task7.startAndEndWithTheSameSymbol(string));
    }

    @Test
    @DisplayName("test8")
    void test8() {
        String string = "101";
        Assertions.assertTrue(Task7.lengthIsMoreOrEqualThan1AndLessOrEqualThan3(string));
    }

    @Test
    @DisplayName("test9")
    void test9() {
        String string = "10";
        Assertions.assertTrue(Task7.lengthIsMoreOrEqualThan1AndLessOrEqualThan3(string));
    }

    @Test
    @DisplayName("test10")
    void test10() {
        String string = "1";
        Assertions.assertTrue(Task7.lengthIsMoreOrEqualThan1AndLessOrEqualThan3(string));
    }

    @Test
    @DisplayName("test11")
    void test11() {
        String string = "IgorGoffman";
        Assertions.assertFalse(Task7.lengthIsMoreOrEqualThan1AndLessOrEqualThan3(string));
    }
}
