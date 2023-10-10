package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        // given
        String s = "01:00";

        // when
        int seconds = Task1.minutesToSeconds(s);

        // then
        assertThat(seconds).isEqualTo(60);
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        // given
        String s = "13:56";

        // when
        int seconds = Task1.minutesToSeconds(s);

        // then
        assertThat(seconds).isEqualTo(836);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        // given
        String s = "10:60";

        // when
        int seconds = Task1.minutesToSeconds(s);

        // then
        assertThat(seconds).isEqualTo(-1);
    }
}
