package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Handler {
    Handler nextHandler = null;

    public abstract Optional<LocalDate> handleRequest(String request);

    public void setNextHandler(Handler next) {
        nextHandler = next;
    }
}
