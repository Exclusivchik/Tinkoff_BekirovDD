package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int a) {
        String s = "" + Math.abs(a);
        while (s.length() > 1) {
            int n = s.length();
            int half = n / 2;
            boolean isPalindrome = true;
            for (int i = 0; i < half; i++) {
                if (s.charAt(i) != s.charAt(n - i - 1)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) {
                return true;
            } else {
                String newS = "";
                for (int i = 0; i < half; i++) {
                    int temp = (s.charAt(2 * i) - '0') + (s.charAt(2 * i + 1) - '0');
                    newS = newS + temp;
                }
                if (n % 2 == 1) {
                    newS += s.charAt(n - 1);
                }
                s = newS;
            }
        }
        return false;
    }
}
