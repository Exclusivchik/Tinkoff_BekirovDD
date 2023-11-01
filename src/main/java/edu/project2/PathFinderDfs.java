package edu.project2;

import java.util.ArrayList;
import java.util.Stack;

public class PathFinderDfs implements PathFinder {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public PathFinderDfs(Cell[][] grid, int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public ArrayList<Coordinates> getPath(Coordinates start, Coordinates finish) {
        int startRow = start.row();
        int startCol = start.col();
        int finishRow = finish.row();
        int finishCol = finish.col();
        Stack<Coordinates> stack = new Stack<>();
        boolean[][] used = new boolean[height][width];
        int[][] dist = new int[height][width];
        Coordinates[][] ancestor = new Coordinates[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                used[i][j] = false;
                dist[i][j] = 0;
            }
        }
        used[startRow][startCol] = true;
        ancestor[startRow][startCol] = null;
        stack.push(start);
        while (!stack.isEmpty()) {
            var tempCell = stack.pop();
            for (var neib : GetNeighborsForCell.get(grid, tempCell)) {
                if (!used[neib.row()][neib.col()]) {
                    int neibRow = neib.row();
                    int neibCol = neib.col();
                    used[neibRow][neibCol] = true;
                    stack.push(neib);
                    dist[neibRow][neibCol] = dist[startRow][startCol] + 1;
                    ancestor[neibRow][neibCol] = tempCell.getCopy();
                }
            }
        }
        if (ancestor[finishRow][finishCol] == null) {
            throw new RuntimeException("Мы сюда не попадём");
        }
        ArrayList<Coordinates> path = new ArrayList<>();
        for (var temp = finish; temp != null; temp = ancestor[temp.row()][temp.col()]) {
            path.add(temp);
        }
        return path;
    }
}
