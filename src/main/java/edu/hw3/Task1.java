package edu.hw3;

public class Task1 {
    private Task1() {
    }

    private static final int ALPH_POWER = 26;

    public static String atbash(String s) {

        String newS = "";
        for (int i = 0; i < s.length(); i++) {
            char sym = s.charAt(i);
            if (Character.isLetter(sym)) {
                char newSym;
                if (Character.isUpperCase(sym)) {
                    newSym = (char) (ALPH_POWER - 1 - (sym - 'A') + 'A');
                } else {
                    newSym = (char) (ALPH_POWER - 1 - (sym - 'a') + 'a');
                }
                newS = newS + newSym;
            } else {
                newS = newS + sym;
            }
        }
        return newS;
    }
}
