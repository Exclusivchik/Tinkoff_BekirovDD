package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task0 {
    private Task0() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void helloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
