package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class YearMonth2Date2Handler extends Handler {
    private static final String FORMAT = "yyyy-MM-dd";

    @Override
    public Optional<LocalDate> handleRequest(String request) {
        try {
            LocalDate parsed = LocalDate.parse(request, DateTimeFormatter.ofPattern(FORMAT));
            return Optional.of(parsed);
        } catch (DateTimeParseException e) {
            if (nextHandler != null) {
                return nextHandler.handleRequest(request);
            } else {
                return Optional.empty();
            }
        }
    }
}
