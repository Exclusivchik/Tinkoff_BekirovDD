package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;

public final class Task6 {
    static final int CAPRECAR = 6174;
    static final int REQUIRED_LEN = 4;
    static final int BASE = 10;

    private Task6() {
    }

    public static int countK(int a, int d) {
        int lenA = ("" + a).length();
        if (lenA != REQUIRED_LEN) {
            return -1;
        }
        if (a == CAPRECAR) {
            return d;
        }
        Integer[] sortedA = new Integer[lenA];
        Integer[] revSortedA = new Integer[lenA];
        int iter = lenA - 1;
        int newA = a;
        while (newA != 0) {
            sortedA[iter] = newA % BASE;
            revSortedA[iter] = newA % BASE;
            iter--;
            newA /= BASE;
        }
        Arrays.sort(sortedA, Comparator.naturalOrder());
        Arrays.sort(revSortedA, Comparator.reverseOrder());

        int x = 0;
        int y = 0;
        int pow = 1;
        int temp;
        for (int i = lenA - 1; i > -1; i--) {
            x += sortedA[i] * pow;
            y += revSortedA[i] * pow;
            pow *= BASE;
        }
        if (x > y) {
            temp = x - y;
        } else {
            temp = y - x;
        }
        while (("" + temp).length() != REQUIRED_LEN) {
            temp *= BASE;
        }
        return countK(temp, d + 1);
    }
}
