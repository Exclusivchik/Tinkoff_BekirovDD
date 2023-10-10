package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(String s) {
        String[] parts = s.split(":");
        if (parts.length == 1) {
            return -1;
        }
        int minutes = Integer.parseInt(parts[0]), seconds = Integer.parseInt(parts[1]);
        if (seconds > 59) {
            return -1;
        }
        int ans = minutes * 60 + seconds;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minutesToSeconds("01:00"));
        System.out.println(minutesToSeconds("13:56"));
        System.out.println(minutesToSeconds("10:60"));
    }
}
