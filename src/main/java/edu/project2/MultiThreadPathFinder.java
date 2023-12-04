package edu.project2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadPathFinder implements PathFinder {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private boolean[][] used;
    private Coordinates[][] ancestor;
    private static final int MAX_AWAIT_MILLISECONDS = 5000;
    private static final int THREADS = 8;
    private final BlockingQueue<Coordinates> queue = new LinkedBlockingQueue<>();
    private final AtomicInteger notUsedCounter;


    public MultiThreadPathFinder(Cell[][] grid, int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = grid;
        notUsedCounter = new AtomicInteger(height * width - 1);
    }

    public ArrayList<Coordinates> getPath(Coordinates start, Coordinates finish) {
        used = new boolean[height][width];
        ancestor = new Coordinates[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                used[i][j] = false;
            }
        }
        used[start.row()][start.col()] = true;
        ancestor[start.row()][start.col()] = null;
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        queue.add(start);
        while (notUsedCounter.get() != 0) {
            try {
                Coordinates tempCell = queue.take();
                executor.execute(() -> processCell(tempCell));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(MAX_AWAIT_MILLISECONDS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ArrayList<Coordinates> path = new ArrayList<>();
        for (var temp = finish; temp != null; temp = ancestor[temp.row()][temp.col()]) {
            path.add(temp);
        }

        return path;
    }

    private void processCell(Coordinates cell) {
        var neibs = GetNeighborsForCell.get(grid, cell);
        for (var neib : neibs) {
            if (!used[neib.row()][neib.col()]) {
                used[neib.row()][neib.col()] = true;
                notUsedCounter.decrementAndGet();
                ancestor[neib.row()][neib.col()] = cell.getCopy();
                queue.add(neib);
            }
        }
    }
}
