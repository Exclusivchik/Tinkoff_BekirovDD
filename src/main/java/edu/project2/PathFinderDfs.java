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
        used[start.row()][start.col()] = true;
        ancestor[start.row()][start.col()] = null;
        stack.push(start);
        while (!stack.isEmpty()) {
            var tempCell = stack.pop();
            for (var neib : findNeibs(tempCell)) {
                if (!used[neib.row()][neib.col()]) {
                    used[neib.row()][neib.col()] = true;
                    stack.push(neib.getCopy());
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

    private ArrayList<Coordinates> findNeibs(Coordinates cell) {
        ArrayList<Coordinates> neibs = new ArrayList<>();
        int row = cell.row();
        int col = cell.col();
        if (row > 0 && !grid[row - 1][col].getWallBottom()) {
            neibs.add(new Coordinates(row - 1, col));
        }
        if (col > 0 && !grid[row][col - 1].getWallRight()) {
            neibs.add(new Coordinates(row, col - 1));
        }
        if (row != height - 1 && !grid[row][col].getWallBottom()) {
            neibs.add(new Coordinates(row + 1, col));
        }
        if (col != width - 1 && !grid[row][col].getWallRight()) {
            neibs.add(new Coordinates(row, col + 1));
        }
        return neibs;
    }
}
