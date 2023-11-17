package edu.hw3;

public final class Task1 {
    private Task1() {
    }

    private static final int ALPH_POWER = 26;

    public static String atbash(String s) {

        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char sym = s.charAt(i);
            if (Character.isLetter(sym)) {
                char newSym;
                if (Character.isUpperCase(sym)) {
                    newSym = (char) (ALPH_POWER - 1 - (sym - 'A') + 'A');
                } else {
                    newSym = (char) (ALPH_POWER - 1 - (sym - 'a') + 'a');
                }
                newS.append(newSym);
            } else {
                newS.append(sym);
            }
        }
        return newS.toString();
    }
}
