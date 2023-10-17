package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindrome(String s) {
        int n = s.length();
        boolean isPalindrome = true;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    public static boolean isPalindromeDescendant(int a) {
        String s = "" + Math.abs(a);
        while (s.length() > 1) {
            int n = s.length();
            if (isPalindrome(s)) {
                return true;
            } else {
                String newS = "";
                for (int i = 0; i + 1 < n; i += 2) {
                    int temp = (s.charAt(i) - '0') + (s.charAt(i + 1) - '0');
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
