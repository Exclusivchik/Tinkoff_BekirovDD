package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project2Test {
    @Test
    @DisplayName("Отсутсвие изолированных ячеек")
    void haveIsolatedCells() {
        int height = 5;
        int width  = 5;
        Maze maze = new Maze(new GeneratorEllerMaze(height, width));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int t = 0;
                if (i == 0) {
                    t++;
                }
                if (j == 0) {
                    t++;
                }
                if (maze.getCellOfGrid(i, j).getWallRight()) {
                    t++;
                }
                if (i > 0 && maze.getCellOfGrid(i - 1, j).getWallBottom()) {
                    t++;
                }
                if (maze.getCellOfGrid(i, j).getWallBottom()) {
                    t++;
                }
                if (j > 0 && maze.getCellOfGrid(i, j - 1).getWallRight()) {
                    t++;
                }
                assertThat(t).isNotEqualTo(4);
            }
        }
    }
}
