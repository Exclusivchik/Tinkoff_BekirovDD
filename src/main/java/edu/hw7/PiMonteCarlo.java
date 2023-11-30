package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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

    public static double multiThreadPi(long simulations) {
        int threads = Runtime.getRuntime().availableProcessors();
        long perThread = simulations / threads;
        final int[] circleCount = {0};

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                int localCircleCount = 0;
                for (int j = 0; j < perThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (x * x + y * y <= 1) {
                        localCircleCount++;
                    }
                }
                circleCount[0] += localCircleCount;
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        }

        return CONST_FOR_FORMULA * circleCount[0] / simulations;
    }
}
