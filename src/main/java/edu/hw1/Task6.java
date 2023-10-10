package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;

public class Task6 {
    public static int countK(int a, int d) {
        int len_a = ("" + a).length();
        if (len_a != 4) {
            return -1;
        }
        if (a == 6174) {
            return d;
        }
        Integer[] s_a = new Integer[len_a], rs_a = new Integer[len_a];
        int iter = len_a - 1;
        while (a != 0) {
            s_a[iter] = a % 10;
            rs_a[iter] = a % 10;
            iter--;
            a /= 10;
        }
        Arrays.sort(s_a, Comparator.naturalOrder());
        Arrays.sort(rs_a, Comparator.reverseOrder());

        int x = 0, y = 0, pow = 1, temp;
        for (int i = len_a - 1; i > -1; i--) {
            x += s_a[i] * pow;
            y += rs_a[i] * pow;
            pow *= 10;
        }
        if (x > y) {
            temp = x - y;
        } else {
            temp = y - x;
        }
        while (("" + temp).length() != 4) {
            temp *= 10;
        }
        return countK(temp, d + 1);
    }

    public static void main(String[] args) {
        System.out.println(countK(6621, 0));
        System.out.println(countK(6554, 0));
        System.out.println(countK(1234, 0));
    }
}
