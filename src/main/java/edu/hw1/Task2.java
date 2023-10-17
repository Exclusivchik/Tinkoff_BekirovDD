package edu.hw1;

public final class Task2 {
    static final int BASE = 10;

    private Task2() {
    }

    public static int countDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0;
        int newn = n;
        while (newn != 0) {
            ans++;
            newn /= BASE;
        }
        return ans;
    }
}
