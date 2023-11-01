package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HangMan {
    static final int MAX_ATTEMPTS = 5;
    private static final Logger LOGGER = LogManager.getLogger();
    private static Scanner input;
    //букв отгадано, кол-во ошибок, количество опечаток, пробовали уже использованную букву
    private final int[] state = {0, 0, 0, 0};
    Guess guesser;

    public HangMan(String source, String hiddenWord) {
        if ("~cin".equals(source)) {
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
        while (state[CodeOfGuess.MISS.code] != MAX_ATTEMPTS && !isEnd(guesser.getTempWord())) {
            LOGGER.info("Guess a letter:");
            String letter = input.nextLine();
            CodeOfGuess code = guesser.guess(letter);
            switch (code) {
                case HIT:
                    LOGGER.info("HIT");
                    state[CodeOfGuess.HIT.code] += countOccurrences(guesser.getHiddenWord(), letter.charAt(0));
                    break;
                case MISS:
                    LOGGER.info("Missed, mistake" + state[1] + " out of " + MAX_ATTEMPTS);
                    state[CodeOfGuess.MISS.code]++;
                    break;
                case TYPO:
                    LOGGER.info("Typo, try again");
                    state[CodeOfGuess.TYPO.code]++;
                    break;
                case USED:
                    LOGGER.info("You have already guessed this letter");
                    state[CodeOfGuess.USED.code]++;
                    break;
                case GIVE_UP:
                    state[CodeOfGuess.MISS.code] = MAX_ATTEMPTS;
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
