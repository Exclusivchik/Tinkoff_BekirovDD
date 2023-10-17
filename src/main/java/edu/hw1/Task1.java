package edu.hw1;

public final class Task1 {
    static final int SECONDS_IN_MINUTES = 60;
    static final int REQUIRED_LEN_AFTER_SPLIT = 2;

    private Task1() {
    }

    public static int minutesToSeconds(String s) {
        String[] parts = s.split(":");
        if (parts.length != REQUIRED_LEN_AFTER_SPLIT) {
            return -1;
        }
        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            if (seconds >= SECONDS_IN_MINUTES) {
                return -1;
            }
            return minutes * SECONDS_IN_MINUTES + seconds;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
