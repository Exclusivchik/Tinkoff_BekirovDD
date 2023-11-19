package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoHandler extends Handler {
    private static final Pattern PATTERN = Pattern.compile("^(\\d+)+ day(s)? ago$");

    @Override
    public Optional<LocalDate> handleRequest(String request) {
        Matcher matcher = PATTERN.matcher(request);
        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(1))));
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        } else {
            return Optional.empty();
        }
    }
}
