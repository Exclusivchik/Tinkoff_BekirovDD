package edu.project2;

public final class Main {
    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        int height = 10;
        int width = 10;
        MazeGenerator eller = new GeneratorEllerMaze(height, width);
        MazeGenerator binaryTree = new GeneratorBinaryTreeMaze(height, width);
        Maze maze = new Maze(eller);
        PathFinder finder = new PathFinderDfs(maze.getGrid(), height, width);
        maze.prettyPrint2();
        maze.printPath(new Coordinates(0, 0), new Coordinates(9, 9), finder);
    }
}
