package edu.hw3;

public class Task1 {
    static final int PREV_UPPER_A = 64;
    static final int PREV_LOWER_A = 96;

    private Task1() {
    }

    public static String atbash(String s) {
        String newS = "";
        int lenS = s.length();
        for (int i = 0; i < lenS; i++) {
            char tempSym = s.charAt(i);
            if (Character.isLetter(tempSym)) {
                if (Character.isUpperCase(tempSym)) {
                    newS = newS + String.valueOf(26 - (tempSym - PREV_LOWER_A));
                }
            } else {
                newS = newS + String.valueOf(tempSym);
            }
        }
        return s;
    }
}
