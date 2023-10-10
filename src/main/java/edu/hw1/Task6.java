package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;

public final class Task6 {
    static final int CAPRECAR = 6174;
    static final int FOUR = 4;
    static final int TEN = 10;

    private Task6() {
    }

    public static int countK(int a, int d) {
        int lenA = ("" + a).length();
        if (lenA != FOUR) {
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
            sortedA[iter] = a % TEN;
            revSortedA[iter] = a % TEN;
            iter--;
            newA /= TEN;
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
            pow *= TEN;
        }
        if (x > y) {
            temp = x - y;
        } else {
            temp = y - x;
        }
        while (("" + temp).length() != FOUR) {
            temp *= TEN;
        }
        return countK(temp, d + 1);
    }
}
