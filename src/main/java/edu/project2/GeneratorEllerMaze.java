package edu.project2;

import java.util.Random;

public class GeneratorEllerMaze implements MazeGenerator {
    private final int height;
    private final int width;
    private Cell[][] grid;
    private int setCounter = 0;
    private final Cell[] tempLine;

    public GeneratorEllerMaze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        tempLine = new Cell[width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void unionSets(int toReplace, int value) {
        for (int i = 0; i < width; i++) {
            if (tempLine[i].getSet() == toReplace) {
                tempLine[i].setSet(value);
            }
        }
    }

    private void createRightWalls() {
        for (int j = 0; j < width - 1; j++) {
            if (tempLine[j].getSet() == tempLine[j + 1].getSet() || new Random().nextBoolean()) {
                tempLine[j].setWallRight(true);
            } else {
                unionSets(tempLine[j + 1].getSet(), tempLine[j].getSet());
            }
        }
        tempLine[width - 1].setWallRight(true);
    }

    private void createBottomWalls() {
        for (int j = 0; j < width; j++) {
            if (calculateSetPower(tempLine, tempLine[j].getSet()) == 1) {
                continue;
            }
            if (new Random().nextBoolean()) {
                tempLine[j].setWallBottom(true);
                tempLine[j].setColor("_");
            }
        }
    }

    private void createLastLine() {
        for (int j = 0; j < width; j++) {
            tempLine[j].setWallBottom(true);
            tempLine[j].setColor("_");
        }
        for (int j = 0; j < width - 1; j++) {
            if (tempLine[j].getSet() != tempLine[j + 1].getSet()) {
                tempLine[j].setWallRight(false);
                unionSets(tempLine[j + 1].getSet(), tempLine[j].getSet());
            }
        }
    }

    private void clearLine() {
        //clearing
        for (int j = 0; j < width; j++) {
            tempLine[j].setWallRight(false);
            if (tempLine[j].getWallBottom()) {
                tempLine[j] = null;
            }
        }
    }

    public Cell[][] generate() {
        fillNa();
        for (int row = 0; row < height; row++) {

            clearLine(); //clear

            fillNa(); //filling empty cells

            createRightWalls(); //creating right walls

            createBottomWalls(); //creating bottom walls

            if (row == height - 1) {
                createLastLine();
            }
            grid[row] = copyLine(tempLine);
        }
        return grid;
    }

    private int calculateSetPower(Cell[] line, int set) {
        int counter = 0;
        for (Cell cell : line) {
            if (cell.getSet() == set && !cell.getWallBottom()) {
                counter++;
            }
        }
        return counter;
    }

    private void fillNa() {
        for (int i = 0; i < tempLine.length; i++) {
            if (tempLine[i] == null) {
                tempLine[i] = new Cell(false, false, -1, " ");
                tempLine[i].setSet(setCounter++);
            }
        }
    }

    private Cell[] copyLine(Cell[] line) {
        Cell[] newLine = new Cell[line.length];
        for (int i = 0; i < line.length; i++) {
            newLine[i] = line[i].getCopy();
        }
        return newLine;
    }
}
