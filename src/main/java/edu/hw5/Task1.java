package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task1 {
    @SuppressWarnings("LineLength")
    private static final Pattern PATTERN =
        Pattern.compile("(^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})$");
    private static final String FORMAT = "yyyy-MM-dd, HH:mm";

    private Task1() {
    }

    public static Duration getDuration(List<String> sessions) {
        int fullDuration  = 0;
        int validSessions = 0;
        for (var session : sessions) {
            Matcher matcher = PATTERN.matcher(session);
            if (matcher.find()) {
                try {
                    validSessions++;
                    LocalDateTime start = LocalDateTime.parse(matcher.group(1), DateTimeFormatter.ofPattern(FORMAT));
                    LocalDateTime end = LocalDateTime.parse(matcher.group(2), DateTimeFormatter.ofPattern(FORMAT));
                    fullDuration += (int) Duration.between(start, end).getSeconds();
                } catch (DateTimeParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return Duration.ofSeconds((long) fullDuration / validSessions);
    }
}
