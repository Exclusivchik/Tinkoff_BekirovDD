package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Тест1")
    void test1(){
        //given
        int n = 8, shift = 1;

        //when
        int ret = Task7.rotateRight(n, shift);

        //then
        assertThat(ret).isEqualTo(4);
    }
    @Test
    @DisplayName("Тест2")
    void test2(){
        //given
        int n = 16, shift = 1;

        //when
        int ret = Task7.rotateLeft(n, shift);

        //then
        assertThat(ret).isEqualTo(1);
    }
    @Test
    @DisplayName("Тест3")
    void test3(){
        //given
        int n = 17, shift = 2;

        //when
        int ret = Task7.rotateLeft(n, shift);

        //then
        assertThat(ret).isEqualTo(6);
    }
}
