package edu.hw5;

import java.util.regex.Pattern;

public final class Task7 {
    private Task7() {
    }

    public static boolean containsMoreOrEqual3SymbolsAndTheThirdIs0(String string) {
        return Pattern.compile("^[01]{2}0[01]*$").matcher(string).find();
    }

    public static boolean startAndEndWithTheSameSymbol(String string) {
        return Pattern.compile("^[01]$|^([01]).*\\1$").matcher(string).find();
    }

    public static boolean lengthIsMoreOrEqualThan1AndLessOrEqualThan3(String string) {
        return Pattern.compile("[01]{1,3}").matcher(string).find();
    }
}
