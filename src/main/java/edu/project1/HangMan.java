package edu.project1;

import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HangMan {
    private final static Logger LOGGER = LogManager.getLogger();
    static final int MAX_ATTEMPTS = 5;
    private static final Scanner INPUT = new Scanner(System.in);
    private int numOfFails = 0;
    private char[] hiddenWord;
    private char[] tempWord;
    private boolean endOfGame = false;

    public void run() {
        var guesser = new Guess();
        while (numOfFails != MAX_ATTEMPTS && !endOfGame) {
            LOGGER.info("Guess a letter:");
            String letter = INPUT.nextLine();
            boolean code = guesser.guess(letter);
            if (!code) {
                continue;
            }
            LOGGER.info("The word: " + new String(tempWord));
        }
        if (endOfGame) {
            LOGGER.info("YOU WIN!!!");
        } else {
            LOGGER.info("YOU LOSE(((");
        }
    }

    static final protected class HardCodeWords {
        private final String[] words = {"hello", "world", "java", "cool",
            "palindrome", "codeforces", "tinkoff", "univercity"};

        public String getString() {
            int index = new Random().nextInt(words.length);
            return words[index];
        }
    }

    final protected class Guess {
        Guess() {
            hiddenWord = new HardCodeWords().getString().toCharArray();
            tempWord = new char[hiddenWord.length];
            for (int i = 0; i < hiddenWord.length; i++) {
                tempWord[i] = '*';
            }
        }

        boolean guess(String a) {
            if (a.equals("~78gofman")) {
                numOfFails = MAX_ATTEMPTS;
                return false;
            }
            if (a.length() != 1) {
                LOGGER.info("Typo, try again");
                return false;
            }
            char letter = a.charAt(0);
            boolean flag = false;
            for (int i = 0; i < tempWord.length; i++) {
                if (letter == hiddenWord[i]) {
                    flag = true;
                    tempWord[i] = letter;
                }
            }
            if (flag) {
                LOGGER.info("Hit");
                int patrickCounter = 0;
                for (char sym : tempWord) {
                    if (sym == '*') {
                        patrickCounter++;
                    }
                }
                if (patrickCounter == 0) {
                    endOfGame = true;
                }
            } else {
                numOfFails++;
                LOGGER.info("Missed, mistake " + numOfFails + " out of 5.");
            }
            return true;
        }
    }
}
