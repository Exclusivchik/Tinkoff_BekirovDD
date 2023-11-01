package edu.project1;

import java.util.Random;

public class RandomWord {
    private RandomWord() {
    }

    private static final String[] WORDS = {"hello", "world", "java", "cool",
        "palindrome", "codeforces", "tinkoff", "univercity", ""};

    public static String get() {
        return WORDS[new Random().nextInt(WORDS.length)];
    }
}
