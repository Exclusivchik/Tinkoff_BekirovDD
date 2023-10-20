package edu.project1;

import java.util.Arrays;

public class Guess {
    // 0 - hit, 1 - miss, 2 - typo, 3 - used, 4 - give up
    static final int HIT = 0;
    static final int MISS = 1;
    static final int TYPO = 2;
    static final int USED = 3;
    static final int GIVE_UP = 4;
    static final int ALPH_POWER = 26;
    private final boolean[] used = new boolean[ALPH_POWER];
    private final String hiddenWord;
    private final char[] tempWord;

    Guess(String word) {
        hiddenWord = word;
        tempWord = new char[hiddenWord.length()];
        Arrays.fill(tempWord, '*');
        Arrays.fill(used, false);
    }

    private boolean checkLetter(String s) {
        if (s.length() != 1) {
            return false;
        }
        char letter = Character.toLowerCase(s.charAt(0));
        return !Character.isDigit(letter);
    }

    public int guess(String requestedString) {
        if (requestedString.equals("~78gofman")) {
            return GIVE_UP;
        }
        if (!checkLetter(requestedString)) {
            return TYPO;
        }
        char letter = Character.toLowerCase(requestedString.charAt(0));
        if (used[letter - 'a']) {
            return USED;
        }
        boolean flag = false;
        for (int i = 0; i < tempWord.length; i++) {
            if (letter == hiddenWord.charAt(i)) {
                flag = true;
                tempWord[i] = letter;
                used[letter - 'a'] = true;
            }
        }
        int code;
        if (flag) {
            code = HIT;
        } else {
            code = MISS;
        }
        return code;
    }

    public char[] getTempWord() {
        return tempWord;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }
}
