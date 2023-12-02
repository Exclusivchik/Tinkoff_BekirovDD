package edu.hw8.Task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;

public class FixedThreadPool implements ThreadPool {
    private final int nThreads;
    private final Thread[] threads;
    private final Queue<Runnable> taskQueue;
    private boolean isShutdown;

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.threads = new Thread[nThreads];
        this.taskQueue = new LinkedList<>();
        isShutdown = false;
    }

    @Override
    public void start() {
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(() -> {
                while (!(isShutdown && taskQueue.isEmpty())) {
                    Runnable task = taskQueue.poll();
                    if (task != null) {
                        task.run();
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isShutdown) {
            taskQueue.add(runnable);
        } else {
            throw new RejectedExecutionException();
        }
    }

    @Override
    public void shutdown() {
        isShutdown = true;
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public void close() {
        shutdown();
    }
}
