package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class TomorrowHandler extends Handler {
    @Override
    public Optional<LocalDate> handleRequest(String request) {
        if ("tomorrow".equals(request)) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        } else {
            return Optional.empty();
        }
    }
}
