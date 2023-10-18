package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Тест1")
    void test1(){
        //given
        int[] a1 = {1, 2, 3, 4}, a2 = {0, 6};

        //when
        boolean ret = Task3.isNestable(a1, a2);

        //then
        assertThat(ret).isEqualTo(true);
    }

    @Test
    @DisplayName("Тест2")
    void test2(){
        //given
        int[] a1 = {3, 1}, a2 = {4, 0};

        //when
        boolean ret = Task3.isNestable(a1, a2);

        //then
        assertThat(ret).isEqualTo(true);
    }

    @Test
    @DisplayName("Тест3")
    void test3(){
        //given
        int[] a1 = {9, 9, 8}, a2 = {8, 9};

        //when
        boolean ret = Task3.isNestable(a1, a2);

        //then
        assertThat(ret).isEqualTo(false);
    }

    @Test
    @DisplayName("Тест4")
    void test4(){
        //given
        int[] a1 = {1, 2, 3, 4}, a2 = {2, 3};

        //when
        boolean ret = Task3.isNestable(a1, a2);

        //then
        assertThat(ret).isEqualTo(false);
    }

}
