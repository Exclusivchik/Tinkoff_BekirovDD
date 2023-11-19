package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class TodayHandler extends Handler {
    @Override
    public Optional<LocalDate> handleRequest(String request) {
        if ("today".equals(request)) {
            return Optional.of(LocalDate.now());
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        } else {
            return Optional.empty();
        }
    }
}
