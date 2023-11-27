package edu.hw7;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

public final class PiMonteCarlo {
    private static final double CONST_FOR_FORMULA = 4.0;

    private PiMonteCarlo() {
    }

    public static double linearPi(long simulations) {
        long circleCount = 0;
        for (int i = 0; i < simulations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }
        return CONST_FOR_FORMULA * circleCount / simulations;
    }

    public static double multiThreadPi(long simulations) throws ExecutionException, InterruptedException {
        int threads = Runtime.getRuntime().availableProcessors();
        long perThread = simulations / threads;
        int circleCount = 0;

        for (int i = 0; i < threads; i++) {
            FutureTask<Integer> futureTask = getIntegerFutureTask(perThread);
            new Thread(futureTask).start();
            circleCount += futureTask.get();
        }

        return CONST_FOR_FORMULA * circleCount / simulations;
    }

    private static FutureTask<Integer> getIntegerFutureTask(long perThread) {
        Callable task = () -> {
            int localCircleCount = 0;
            for (int j = 0; j < perThread; j++) {
                double x = ThreadLocalRandom.current().nextDouble();
                double y = ThreadLocalRandom.current().nextDouble();
                if (x * x + y * y <= 1) {
                    localCircleCount++;
                }
            }
            return localCircleCount;
        };
        return new FutureTask<Integer>(task);
    }
}
