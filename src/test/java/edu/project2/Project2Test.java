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
}
