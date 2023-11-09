package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Task2Test {
    @Test
    @DisplayName("test1")
    void test1() {
        int year = 1925;
        List<LocalDate> required = List.of(
            LocalDate.of(year, Month.FEBRUARY, 13),
            LocalDate.of(year, Month.MARCH, 13),
            LocalDate.of(year, Month.NOVEMBER, 13)
        );
        Assertions.assertEquals(Task2.getAllFridaysThe13(year), required);
    }

    @Test
    @DisplayName("test2")
    void test2() {
        int year = 2024;
        List<LocalDate> required = List.of(
            LocalDate.of(year, Month.SEPTEMBER, 13),
            LocalDate.of(year, Month.DECEMBER, 13)
        );
        Assertions.assertEquals(Task2.getAllFridaysThe13(year), required);
    }

    @Test
    @DisplayName("test3")
    void test3() {
        Assertions.assertEquals(Task2.getNextFridayThe13(), LocalDate.of(2024, 9, 13));
    }
}
