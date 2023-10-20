package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HangMan {
    static final int MAX_ATTEMPTS = 5;
    static final int HIT = 0;
    static final int MISS = 1;
    static final int GIVE_UP = 2;
    static final int TYPO = 3;
    static final int USED = 4;
    private final static Logger LOGGER = LogManager.getLogger();
    private static final Scanner INPUT = new Scanner(System.in);
    private int numOfFails = 0;

    private boolean isEnd(char[] tempWord) {
        int patrikCounter = tempWord.length;
        for (char sym : tempWord) {
            if (sym != '*') {
                patrikCounter--;
            }
        }
        return patrikCounter == 0;
    }

    public void run() {
        LOGGER.info("INFO: to give up enter ~78gofman");
        var guesser = new Guess();
        while (numOfFails != MAX_ATTEMPTS && !isEnd(guesser.getTempWord())) {
            LOGGER.info("Guess a letter:");
            String letter = INPUT.nextLine();
            int code = guesser.guess(letter);
            switch (code) {
                case HIT:
                    LOGGER.info("HIT");
                    break;
                case MISS:
                    numOfFails++;
                    LOGGER.info("Missed, mistake" + numOfFails + " out of " + MAX_ATTEMPTS);
                    break;
                case TYPO:
                    LOGGER.info("Typo, try again");
                    break;
                case USED:
                    LOGGER.info("You have already guessed this letter");
                    break;
                case GIVE_UP:
                    numOfFails = MAX_ATTEMPTS;
                    break;
                default:
                    break;
            }
            LOGGER.info("The word: " + new String(guesser.getTempWord()));
        }
        if (isEnd(guesser.getTempWord())) {
            LOGGER.info("YOU WIN!!!");
        } else {
            LOGGER.info("YOU LOSE(((");
        }
    }
}
