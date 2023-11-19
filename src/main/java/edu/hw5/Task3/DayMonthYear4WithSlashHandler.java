package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DayMonthYear4WithSlashHandler extends Handler {
    private static final String FORMAT = "d/M/yyyy";

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
