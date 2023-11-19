package edu.hw5;

import java.util.regex.Pattern;

public final class Task4 {
    private Task4() {
    }

    public static boolean isSafePassword(String password) {
        return Pattern.compile(".*[-+_!@#$%^&*.,?].*").matcher(password).find();
    }
}
