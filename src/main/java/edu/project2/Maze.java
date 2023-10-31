package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private int setCounter = 0;
    private final Cell[] tempLine;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        tempLine = new Cell[width];
        generateEllerMaze();
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

    private void generateEllerMaze() {
        fillNa();
        for (int row = 0; row < height; row++) {
            //clear
            clearLine();
            //filling empty cells
            fillNa();
            //creating right walls
            createRightWalls();
            //creating bottom walls
            createBottomWalls();

            if (row == height - 1) {
                createLastLine();
            }
            grid[row] = copyLine(tempLine);
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
            String color = temp.getColor();
            if (temp.getWallBottom() && temp.getWallRight()) {
                stringLine += "_" + color + "_│";
            } else if (temp.getWallBottom()) {
                stringLine += "_" + color + "__";
            } else if (temp.getWallRight()) {
                stringLine += " " + color + " │";
            } else {
                stringLine += " " + color + "  ";
            }
        }
        System.out.println(stringLine);
    }

    public Cell getCellOfGrid(int row, int col) {
        return grid[row][col].getCopy();
    }

    public ArrayList<Coordinates> findPathBfs(Coordinates start, Coordinates finish) {
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
            for (var neib: findNeibs(tempCell)) {
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

    public void printPath(Coordinates start, Coordinates stop) {
        var path = findPathBfs(start, stop);
        for (var temp: path) {
            grid[temp.row()][temp.col()].setColor("■");
        }
        prettyPrint2();
        for (var temp: path) {
            if (grid[temp.row()][temp.col()].getWallBottom()) {
                grid[temp.row()][temp.col()].setColor("_");
            } else {
                grid[temp.row()][temp.col()].setColor(" ");
            }
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void printSetsOfLine(Cell[] line) {
        for (int i = 0; i < width; i++) {
            System.out.print(line[i].getSet() + " ");
        }
        System.out.println();
    }
}
