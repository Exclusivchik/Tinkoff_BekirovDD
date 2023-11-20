package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final double FAILURE_PROBABILITY = 1;

    @Override
    public void execute(String command) throws ConnectionException {
        LOGGER.info("Executing command: " + command + " (Faulty Connection)");

        if (shouldFail()) {
            LOGGER.info("ConnectionException occurred");
            throw new ConnectionException("Failed to execute command on faulty connection", new RuntimeException());
        }
    }

    @Override
    public void close() {
        LOGGER.info("Closing faulty connection");
    }

    private boolean shouldFail() {
        Random random = new Random();
        return random.nextDouble() < FAILURE_PROBABILITY;
    }
}
