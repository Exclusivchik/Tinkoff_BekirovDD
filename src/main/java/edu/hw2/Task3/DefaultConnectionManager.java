package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double FAULTY_CONNECTION_PROBABILITY = 0.2;

    @Override
    public Connection getConnection() {
        if (shouldReturnFaultyConnection()) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }

    private boolean shouldReturnFaultyConnection() {
        Random random = new Random();
        return random.nextDouble() < FAULTY_CONNECTION_PROBABILITY;
    }
}
