package edu.hw5;

import java.util.regex.Pattern;

public final class Task8 {
    private Task8() {
    }

    public static boolean isOddLength(String string) {
        return Pattern.compile("^[01]([01][01])*$").matcher(string).find();
    }

    public static boolean startWith0AndOddLengthOrStartWith1AndEvenLength(String string) {
        return Pattern.compile("^0([01][01])*$|^1[01]([01][01])*$").matcher(string).find();
    }

    public static boolean zeroCountDividesBy3(String string) {
        return Pattern.compile("^(1*01*01*01*)*$").matcher(string).find();
    }

    public static boolean everyOddSymIsOne(String string) {
        return Pattern.compile("^(1[01])*1?$").matcher(string).find();
    }
}
