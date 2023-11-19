package edu.hw5;

import java.util.regex.Pattern;

public final class Task6 {
    private Task6() {
    }

    public static boolean isSubsequence(String needle, String haystack) {
        StringBuilder regExpr = new StringBuilder();
        for (var sym : needle.toCharArray()) {
            regExpr.append(".*").append(sym);
        }
        regExpr.append(".*");
        return Pattern.compile(regExpr.toString()).matcher(haystack).find();
    }
}
