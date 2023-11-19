package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private static final int REQUIRED_DATE = 13;

    private Task2() {
    }

    public static List<LocalDate> getAllFridaysThe13(int year) {
        LocalDate tempDate = LocalDate.of(year, Month.JANUARY, 1);
        List<LocalDate> allFridaysThe13 = new ArrayList<>();
        while (tempDate.getYear() != year + 1) {
            if (tempDate.getDayOfWeek() == DayOfWeek.FRIDAY && tempDate.getDayOfMonth() == REQUIRED_DATE) {
                allFridaysThe13.add(tempDate);
            }
            tempDate = tempDate.plusDays(1L);
        }
        return allFridaysThe13;
    }

    public static LocalDate getNextFridayThe13() {
        LocalDate tempDate = LocalDate.now();
        while (tempDate.getDayOfMonth() != REQUIRED_DATE) {
            tempDate = tempDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return tempDate;
    }
}
