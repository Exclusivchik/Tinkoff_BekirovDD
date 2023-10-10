package edu.hw1;

public class Task4 {
    public static String fixString(String s) {
        int n = s.length(), half = s.length() / 2;
        char a[] = new char[n];
        for (int i = 0; i < half; i++) {
            a[i * 2] = s.charAt(i * 2 + 1);
            a[i * 2 + 1] = s.charAt(i * 2);
        }
        if (n % 2 == 1) {
            a[n - 1] = s.charAt(n - 1);
        }
        String ret = new String(a);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(fixString("123456"));
        System.out.println(fixString("hTsii  s aimex dpus rtni.g"));
        System.out.println(fixString("badce"));
    }
}
