package edu.project2;

import java.util.Random;

public class GeneratorBinaryTreeMaze implements MazeGenerator {
    private final int height;
    private final int width;
    private Cell[][] grid;

    public GeneratorBinaryTreeMaze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        init();
    }

    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(true, true, -1, "_");
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] generate() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row != height - 1) {
                    if (new Random().nextBoolean() && col != width - 1) {
                        grid[row][col].setWallRight(false);
                    } else {
                        grid[row][col].setWallBottom(false);
                        grid[row][col].setColor(" ");
                    }
                } else if (col != width - 1) {
                    grid[row][col].setWallRight(false);
                }
            }
        }
        return grid;
    }
}
