package edu.project2;

import java.util.ArrayList;

public final class GetNeighborsForCell {
    private GetNeighborsForCell() {
    }

    public static ArrayList<Coordinates> get(Cell[][] grid, Coordinates cell) {
        int height = grid.length;
        if (height == 0) {
            throw new IllegalArgumentException("Пустой лабиринт");
        }
        int width = grid[0].length;
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
