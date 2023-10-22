package edu.hw2.Task3;

public final class Main {
    static final int MAX_ATTEMPTS = 3;

    private Main() {
    }

    public static void main(String[] args) {
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = MAX_ATTEMPTS;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        executor.updatePackages();
    }
}
