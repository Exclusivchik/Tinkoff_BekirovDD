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
        for (int row = 0; row < height; row++) {
            Cell[] tempLine = copyLine(origin);

            //printSetsOfLine(tempLine);
            if (row != height - 1) {
                //clearing
                for (int j = 0; j < width; j++) {
                    tempLine[j].setWallRight(false);
                    if (tempLine[j].getWallBottom()) {
                        tempLine[j] = null;
                    }
                }
                //filling empty cells
                tempLine = fillNa(tempLine);

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
                    if (calculateSetPower(tempLine, tempLine[j].getSet()) == 1) {
                        continue;
                    }
                    if (new Random().nextBoolean()) {
                        tempLine[j].setWallBottom(true);
                        tempLine[j].setColor("_");
                    }
                }
            } else {
                for (int j = 0; j < width; j++) {
                    tempLine[j].setWallBottom(true);
                    tempLine[j].setColor("_");
                }
                for (int j = 0; j < width - 1; j++) {
                    if (tempLine[j].getSet() != tempLine[j + 1].getSet()) {
                        tempLine[j].setWallRight(false);
                        tempLine[j + 1].setSet(tempLine[j].getSet());
                    }
                }
            }
            tempLine[width - 1].setWallRight(true);

            grid[row] = copyLine(tempLine);
            origin = copyLine(tempLine);
        }
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

    private Cell[] fillNa(Cell[] line) {
        Cell[] response = new Cell[line.length];
        for (int i = 0; i < line.length; i++) {
            if (line[i] == null) {
                response[i] = new Cell(false, false, -1, " ");
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
            System.out.print("____");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            printLine(grid[i]);
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void printLine(Cell[] line) {
        String stringLine = "│";
        for (int j = 0; j < width; j++) {
            Cell temp = line[j];
            //String color = temp.getColor();
            if (temp.getWallBottom() && temp.getWallRight()) {
                stringLine += "_" + "_│";
            } else if (temp.getWallBottom()) {
                stringLine += "_"  + "__";
            } else if (temp.getWallRight()) {
                stringLine += " "  + " │";
            } else {
                stringLine += " "  + "  ";
            }
        }
        System.out.println(stringLine);
    }

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public boolean haveIsolated() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int t = 0;
                if (i == 0 || j == 0) {
                    t++;
                }
                if (grid[i][j].getWallRight()) {
                    t++;
                }
                if (i > 0 && grid[i - 1][j].getWallBottom()) {
                    t++;
                }
                if (grid[i][j].getWallBottom()) {
                    t++;
                }
                if (j > 0 && grid[i][j - 1].getWallRight()) {
                    t++;
                }
                if (t == 4) {
                    System.out.println(i + " " + j);
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void printSetsOfLine(Cell[] line) {
        for (int i = 0; i < width; i++) {
            System.out.print(line[i].getSet() + " ");
        }
        System.out.println();
    }
}
