package edu.hw1;

import java.util.ArrayList;
import java.util.List;

public final class Task8 {
    static final int EIGHT = 8;
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int MINUS_ONE = -1;
    static final int TWO = 2;
    static final int MINUS_TWO = -2;

    private Task8() {
    }

    public static List<int[]> getNeibs(int x, int y) {
        ArrayList<int[]> neibs = new ArrayList<>();
        int[] plusMinusOne = {MINUS_ONE, ONE};
        int[] plusMinusTwo = {MINUS_TWO, TWO};
        for (int k : plusMinusOne) {
            for (int l : plusMinusTwo) {
                if (ZERO <= x + k && x + k < EIGHT && ZERO <= y + l && y + l < EIGHT) {
                    neibs.add(new int[] {x + k, y + l});
                }
            }
        }
        for (int k : plusMinusTwo) {
            for (int l : plusMinusOne) {
                if (ZERO <= x + k && x + k < EIGHT && ZERO <= y + l && y + l < EIGHT) {
                    neibs.add(new int[] {x + k, y + l});
                }
            }
        }
        return neibs;
    }

    public static boolean knightBoardCapture(int[][] a) {
        boolean flag = true;
        for (int i = 0; i < EIGHT; i++) {
            for (int j = 0; j < EIGHT; j++) {
                if (a[i][j] != ONE) {
                    continue;
                }
                List<int[]> neibs = getNeibs(i, j);
                for (int[] neib : neibs) {
                    if (a[neib[0]][neib[1]] == ONE) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }

}
