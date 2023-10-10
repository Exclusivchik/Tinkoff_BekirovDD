package edu.hw1;

public class Task5 {
    public static boolean isPalindromeDescendant(int a) {
        if(a < 0){
            a = -a;
        }
        String s = "" + a;
        while (s.length() > 1) {
            int n = s.length();
            int half = n / 2;
            boolean is_palindrome = true;
            for (int i = 0; i < half; i++) {
                if (s.charAt(i) != s.charAt(n - i - 1)) {
                    is_palindrome = false;
                    break;
                }
            }
            if (is_palindrome) {
                return true;
            } else {
                String new_s = "";
                for (int i = 0; i < half; i++) {
                    int temp = (s.charAt(2 * i) - '0') + (s.charAt(2 * i + 1) - '0');
                    new_s = new_s + temp;
                }
                if(n % 2 == 1){
                    new_s += s.charAt(n - 1);
                }
                s = new_s;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromeDescendant(11211230));
        System.out.println(isPalindromeDescendant(13001120));
        System.out.println(isPalindromeDescendant(23336014));
        System.out.println(isPalindromeDescendant(11));
        System.out.println(isPalindromeDescendant(23));
        System.out.println(isPalindromeDescendant(123123123));
        System.out.println(isPalindromeDescendant(1));
        System.out.println(isPalindromeDescendant(-11));
    }
}
