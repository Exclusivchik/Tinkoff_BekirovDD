package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        int a = 6621;

        //when
        int ret = Task6.countK(a, 0);

        //then
        assertThat(ret).isEqualTo(5);
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        int a = 6554;

        //when
        int ret = Task6.countK(a, 0);

        //then
        assertThat(ret).isEqualTo(4);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        int a = 1234;

        //when
        int ret = Task6.countK(a, 0);

        //then
        assertThat(ret).isEqualTo(3);
    }
}
