package edu.project1;

import java.util.Arrays;

public class Guess {
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

    public CodeOfGuess guess(String requestedString) {
        if ("~78gofman".equals(requestedString)) {
            return CodeOfGuess.GIVE_UP;
        }
        if (!checkLetter(requestedString)) {
            return CodeOfGuess.TYPO;
        }
        char letter = Character.toLowerCase(requestedString.charAt(0));
        if (used[letter - 'a']) {
            return CodeOfGuess.USED;
        }
        boolean flag = false;
        for (int i = 0; i < tempWord.length; i++) {
            if (letter == hiddenWord.charAt(i)) {
                flag = true;
                tempWord[i] = letter;
                used[letter - 'a'] = true;
            }
        }
        CodeOfGuess code;
        if (flag) {
            code = CodeOfGuess.HIT;
        } else {
            code = CodeOfGuess.MISS;
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
