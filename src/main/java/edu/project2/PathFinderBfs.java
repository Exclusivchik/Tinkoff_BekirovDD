package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathFinderBfs implements PathFinder {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public PathFinderBfs(Cell[][] grid, int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public ArrayList<Coordinates> getPath(Coordinates start, Coordinates finish) {
        LinkedList<Coordinates> queue = new LinkedList<>();
        boolean[][] used = new boolean[height][width];
        int[][] dist = new int[height][width];
        Coordinates[][] ancestor = new Coordinates[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                used[i][j] = false;
                dist[i][j] = 0;
            }
        }
        used[start.row()][start.col()] = true;
        ancestor[start.row()][start.col()] = null;
        queue.add(start);
        while (!queue.isEmpty()) {
            var tempCell = queue.poll();
            var neibs = GetNeighborsForCell.get(grid, tempCell);
            for (var neib : neibs) {
                if (!used[neib.row()][neib.col()]) {
                    used[neib.row()][neib.col()] = true;
                    queue.add(neib.getCopy());
                    dist[neib.row()][neib.col()] = dist[start.row()][start.col()] + 1;
                    ancestor[neib.row()][neib.col()] = tempCell.getCopy();
                }
            }
        }
        ArrayList<Coordinates> path = new ArrayList<>();
        for (var temp = finish; temp != null; temp = ancestor[temp.row()][temp.col()]) {
            path.add(temp);
        }
        return path;
    }
}
