package edu.project2;

public class Main {
    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        maze.prettyPrint2();
        maze.printPath(new Coordinates(0, 0), new Coordinates(7, 8));
        maze.printPath(new Coordinates(3, 4), new Coordinates(9, 9));
    }
}
