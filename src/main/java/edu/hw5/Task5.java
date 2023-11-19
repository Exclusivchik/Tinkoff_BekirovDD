package edu.hw5;

import java.util.regex.Pattern;

public final class Task5 {
    private Task5() {
    }

    public static boolean isRussianCarNumber(String carNumber) {
        return Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$").matcher(carNumber).find();
    }
}
