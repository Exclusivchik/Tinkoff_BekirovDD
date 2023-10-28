package edu.project1;

public enum CodeOfGuess {
    HIT(0),
    MISS(1),
    TYPO(2),
    USED(3),
    GIVE_UP(4);
    public final int code;

    CodeOfGuess(int code) {
        this.code = code;
    }
}
