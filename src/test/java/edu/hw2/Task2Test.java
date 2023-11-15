package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("testForRectangle")
    void test1() {
        Rectangle rect = new Rectangle(10, 20);
        rect = rect.setHeight(20);
        assertThat(rect.area()).isEqualTo(400);
    }

    @Test
    @DisplayName("testForSquare")
    void test2() {
        Square square = new Square(10);
        square = square.setHeight(20);
        assertThat(square.area()).isEqualTo(400);
    }
}
