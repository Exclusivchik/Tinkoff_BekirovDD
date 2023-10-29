package edu.project2;

public class Main {
    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        maze.prettyPrint2();
    }
}
