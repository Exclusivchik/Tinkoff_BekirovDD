package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project2Test {
    private final int height = 10;
    private final int width = 10;

    @Test
    @DisplayName("Проверка на связность Эллерова лабиринта")
    void isLinked() {
        var generator = new GeneratorEllerMaze(height, width);
        Maze maze = new Maze(generator);
        var grid = maze.getGrid();
        Stack<Coordinates> stack = new Stack<>();
        boolean[][] used = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                used[i][j] = false;
            }
        }
        stack.push(new Coordinates(0, 0));
        while (!stack.empty()) {
            var tempCell = stack.pop();
            for (var neib : GetNeighborsForCell.get(grid, tempCell)) {
                if (!used[neib.row()][neib.col()]) {
                    used[neib.row()][neib.col()] = true;
                    stack.push(neib);
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertThat(used[i][j]).isNotEqualTo(false);
            }
        }
    }

    @Test
    @DisplayName("Проверка на связность лабиринта бинарного дерева")
    void isLinked2() {
        var generator = new GeneratorBinaryTreeMaze(height, width);
        Maze maze = new Maze(generator);
        var grid = maze.getGrid();
        Stack<Coordinates> stack = new Stack<>();
        boolean[][] used = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                used[i][j] = false;
            }
        }
        stack.push(new Coordinates(0, 0));
        while (!stack.empty()) {
            var tempCell = stack.pop();
            for (var neib : GetNeighborsForCell.get(grid, tempCell)) {
                if (!used[neib.row()][neib.col()]) {
                    used[neib.row()][neib.col()] = true;
                    stack.push(neib);
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertThat(used[i][j]).isNotEqualTo(false);
            }
        }
    }

    @Test
    @DisplayName("Проверка на адекватность DFS")
    void testDFS() {
        var generator = new GeneratorEllerMaze(height, width);
        Maze maze = new Maze(generator);
        var grid = maze.getGrid();
        var solver = new PathFinderDfs(grid, height, width);
        var path = solver.getPath(new Coordinates(0, 0), new Coordinates(9, 9));
        for (int i = 0; i < path.size() - 1; i++) {
            var coord1 = path.get(i);
            var coord2 = path.get(i + 1);
            if (coord1.row() == coord2.row()) {
                if (coord1.col() < coord2.col()) {
                    assertThat(grid[coord1.row()][coord1.col()].getWallRight()).isFalse();
                } else {
                    assertThat(grid[coord2.row()][coord2.col()].getWallRight()).isFalse();
                }
            } else {
                if (coord1.row() < coord2.row()) {
                    assertThat(grid[coord1.row()][coord1.col()].getWallBottom()).isFalse();
                } else {
                    assertThat(grid[coord2.row()][coord2.col()].getWallBottom()).isFalse();
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка на адекватность BFS")
    void testBFS() {
        var generator = new GeneratorEllerMaze(height, width);
        Maze maze = new Maze(generator);
        var grid = maze.getGrid();
        var solver = new PathFinderBfs(grid, height, width);
        var path = solver.getPath(new Coordinates(0, 0), new Coordinates(9, 9));
        for (int i = 0; i < path.size() - 1; i++) {
            var coord1 = path.get(i);
            var coord2 = path.get(i + 1);
            if (coord1.row() == coord2.row()) {
                if (coord1.col() < coord2.col()) {
                    assertThat(grid[coord1.row()][coord1.col()].getWallRight()).isFalse();
                } else {
                    assertThat(grid[coord2.row()][coord2.col()].getWallRight()).isFalse();
                }
            } else {
                if (coord1.row() < coord2.row()) {
                    assertThat(grid[coord1.row()][coord1.col()].getWallBottom()).isFalse();
                } else {
                    assertThat(grid[coord2.row()][coord2.col()].getWallBottom()).isFalse();
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка на адекватность многопоточного BFS")
    void testMultithreadBFS() {
        var generator = new GeneratorEllerMaze(height, width);
        Maze maze = new Maze(generator);
        var grid = maze.getGrid();
        var solver = new MultiThreadPathFinder(grid, height, width);
        var path = solver.getPath(new Coordinates(0, 0), new Coordinates(9, 9));
        for (int i = 0; i < path.size() - 1; i++) {
            var coord1 = path.get(i);
            var coord2 = path.get(i + 1);
            if (coord1.row() == coord2.row()) {
                if (coord1.col() < coord2.col()) {
                    assertThat(grid[coord1.row()][coord1.col()].getWallRight()).isFalse();
                } else {
                    assertThat(grid[coord2.row()][coord2.col()].getWallRight()).isFalse();
                }
            } else {
                if (coord1.row() < coord2.row()) {
                    assertThat(grid[coord1.row()][coord1.col()].getWallBottom()).isFalse();
                } else {
                    assertThat(grid[coord2.row()][coord2.col()].getWallBottom()).isFalse();
                }
            }
        }
    }
}
