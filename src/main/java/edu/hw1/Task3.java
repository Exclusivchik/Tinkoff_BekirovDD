package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    public static int min(int[] a) {
        int min = a[0];
        for (int el : a) {
            if (el < min) {
                min = el;
            }
        }
        return min;
    }

    public static int max(int[] a) {
        int max = a[0];
        for (int el : a) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        boolean ret = false;
        if (a1.length < 2 || a2.length < 2) {
            return false;
        }
        if (min(a2) < min(a1) && max(a1) < max(a2)) {
            ret = true;
        }
        return ret;
    }
}
