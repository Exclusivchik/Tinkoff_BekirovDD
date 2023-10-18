package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String s) {
        int n = s.length();
        int half = s.length() / 2;
        char[] a = new char[n];
        for (int i = 0; i < half; i++) {
            a[i * 2] = s.charAt(i * 2 + 1);
            a[i * 2 + 1] = s.charAt(i * 2);
        }
        if (n % 2 == 1) {
            a[n - 1] = s.charAt(n - 1);
        }
        return new String(a);
    }
}
