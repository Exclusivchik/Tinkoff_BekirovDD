package edu.hw1;

public class Task2 {
    public static int countDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0;
        while (n != 0) {
            ans++;
            n /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countDigits(4666));
        System.out.println(countDigits(544));
        System.out.println(countDigits(0));
        System.out.println(countDigits(-100));
    }
}
