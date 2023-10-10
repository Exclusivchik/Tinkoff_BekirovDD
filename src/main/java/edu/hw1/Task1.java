package edu.hw1;

public final class Task1 {
    static final int SECONDS_IN_MINUTES = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String s) {
        String[] parts = s.split(":");
        if (parts.length == 1) {
            return -1;
        }
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        if (seconds >= SECONDS_IN_MINUTES) {
            return -1;
        }
        int ans = minutes * SECONDS_IN_MINUTES + seconds;
        return ans;
    }
}
