package edu.project2;

import java.util.Random;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private int setCounter = 0;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        generateEllerMaze();
    }

    private void generateEllerMaze() {
        Cell[] origin = fillNa(new Cell[width]);
        for (int i = 0; i < height; i++) {
            Cell[] tempLine = copyLine(origin);

            //clearing
            for (int j = 0; j < width; j++) {
                tempLine[j].setWallRight(false);
                if (tempLine[j].getWallBottom()) {
                    tempLine[j] = null;
                }
            }

            //filling empty cells
            tempLine = fillNa(tempLine);
            if (i != height - 1) {
                //creating right walls
                for (int j = 0; j < width - 1; j++) {
                    if (tempLine[j].getSet() == tempLine[j + 1].getSet()) {
                        tempLine[j].setWallRight(true);
                        continue;
                    }
                    if (new Random().nextBoolean()) {
                        tempLine[j].setWallRight(true);
                    } else {
                        tempLine[j + 1].setSet(tempLine[j].getSet());
                    }
                }

                //creating bottom walls
                for (int j = 0; j < width; j++) {
                    if (calculateSetPower(tempLine, tempLine[i].getSet()) == 1) {
                        continue;
                    }
                    if (new Random().nextBoolean()) {
                        tempLine[j].setWallBottom(true);
                    }
                }
            } else {
                for (int j = 0; j < width; j++) {
                    tempLine[j].setWallBottom(true);
                }
                for (int j = 0; j < width - 1; j++) {
                    if (tempLine[j].getSet() != tempLine[j + 1].getSet()) {
                        tempLine[j].setWallRight(false);
                        tempLine[j + 1].setSet(tempLine[j].getSet());
                    }
                }
            }

            grid[i] = copyLine(tempLine);
            origin = copyLine(tempLine);
        }
    }

    private int calculateSetPower(Cell[] line, int set) {
        int counter = 0;
        for (Cell cell : line) {
            if (cell.getSet() == set) {
                counter++;
            }
        }
        return counter;
    }

    private Cell[] fillNa(Cell[] line) {
        Cell[] response = new Cell[line.length];
        for (int i = 0; i < line.length; i++) {
            if (line[i] == null) {
                response[i] = new Cell();
                response[i].setSet(setCounter++);
            } else {
                response[i] = line[i].getCopy();
            }
        }
        return response;
    }

    private Cell[] copyLine(Cell[] line) {
        Cell[] newLine = new Cell[line.length];
        for (int i = 0; i < line.length; i++) {
            newLine[i] = line[i].getCopy();
        }
        return newLine;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void prettyPrint2() {
        for (int i = 0; i < width; i++) {
            System.out.print("_____");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print("│");
            for (int j = 0; j < width; j++) {
                var temp = grid[i][j];
                if (temp.getWallBottom() && temp.getWallRight()) {
                    System.out.print(" _" + "_" + "_│");
                } else if (temp.getWallBottom()) {
                    System.out.print(" _" + "_" + "_ ");
                } else if (temp.getWallRight()) {
                    System.out.print("  " + " " + " │");
                } else {
                    System.out.print("  " + " " + "  ");
                }
            }
            System.out.println();
        }
    }
}
