package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HangMan {
    static final int MAX_ATTEMPTS = 5;
    static final int HIT = 0;
    static final int MISS = 1;
    static final int TYPO = 2;
    static final int USED = 3;
    static final int GIVE_UP = 4;
    private final static Logger LOGGER = LogManager.getLogger();
    private static Scanner input;
    //букв отгадано, кол-во ошибок, количество опечаток, пробовали уже использованную букву
    private final int[] state = {0, 0, 0, 0};
    Guess guesser;

    public HangMan(String source, String hiddenWord) {
        if (source.equals("~cin")) {
            input = new Scanner(System.in);
        } else {
            input = new Scanner(source);
        }
        guesser = new Guess(hiddenWord);
    }

    private boolean isEnd(char[] tempWord) {
        int patrikCounter = tempWord.length;
        for (char sym : tempWord) {
            if (sym != '*') {
                patrikCounter--;
            }
        }
        return patrikCounter == 0;
    }

    private static int countOccurrences(String str, char ch) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }
        return counter;
    }

    public String run() {
        LOGGER.info("INFO: to give up enter ~78gofman");

        if (guesser.getTempWord().length == 0) {
            LOGGER.info("Incorrect length of word");
            return "INCORRECT";
        }
        while (state[MISS] != MAX_ATTEMPTS && !isEnd(guesser.getTempWord())) {
            LOGGER.info("Guess a letter:");
            String letter = input.nextLine();
            int code = guesser.guess(letter);
            switch (code) {
                case HIT:
                    LOGGER.info("HIT");
                    state[HIT] += countOccurrences(guesser.getHiddenWord(), letter.charAt(0));
                    break;
                case MISS:
                    LOGGER.info("Missed, mistake" + state[1] + " out of " + MAX_ATTEMPTS);
                    state[MISS]++;
                    break;
                case TYPO:
                    LOGGER.info("Typo, try again");
                    state[TYPO]++;
                    break;
                case USED:
                    LOGGER.info("You have already guessed this letter");
                    state[USED]++;
                    break;
                case GIVE_UP:
                    state[MISS] = MAX_ATTEMPTS;
                    break;
                default:
                    break;
            }
            LOGGER.info("The word: " + new String(guesser.getTempWord()));
        }
        String result;
        if (isEnd(guesser.getTempWord())) {
            LOGGER.info("YOU WIN!!!");
            result = "WIN";
        } else {
            LOGGER.info("YOU LOSE(((");
            result = "LOSE";
        }
        return result;
    }

    public int[] getState() {
        return state;
    }
}
