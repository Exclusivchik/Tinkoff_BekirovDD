package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        // given
        int n = 4666;

        // when
        int cnt = Task2.countDigits(n);

        // then
        assertThat(cnt).isEqualTo(4);
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        // given
        int n = 544;

        // when
        int cnt = Task2.countDigits(n);

        // then
        assertThat(cnt).isEqualTo(3);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        // given
        int n = 0;

        // when
        int cnt = Task2.countDigits(n);

        // then
        assertThat(cnt).isEqualTo(1);
    }

    @Test
    @DisplayName("Тест4")
    void test4() {
        // given
        int n = -78;

        // when
        int cnt = Task2.countDigits(n);

        // then
        assertThat(cnt).isEqualTo(2);
    }
}
