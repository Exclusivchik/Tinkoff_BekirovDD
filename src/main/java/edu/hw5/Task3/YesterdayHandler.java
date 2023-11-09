package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class YesterdayHandler extends Handler {
    @Override
    public Optional<LocalDate> handleRequest(String request) {
        if ("yesterday".equals(request)) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        } else {
            return Optional.empty();
        }
    }
}
