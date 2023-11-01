package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(MazeGenerator generator) {
        this.height = generator.getHeight();
        this.width = generator.getWidth();
        grid = generator.generate();
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

    public Cell[][] getGrid() {
        return grid;
    }

    public void printPath(Coordinates start, Coordinates stop, PathFinder finder) {
        var path = finder.getPath(start, stop);
        for (var temp : path) {
            grid[temp.row()][temp.col()].setColor("■");
        }
        prettyPrint2();
        for (var temp : path) {
            if (grid[temp.row()][temp.col()].getWallBottom()) {
                grid[temp.row()][temp.col()].setColor("_");
            } else {
                grid[temp.row()][temp.col()].setColor(" ");
            }
        }
    }
}
