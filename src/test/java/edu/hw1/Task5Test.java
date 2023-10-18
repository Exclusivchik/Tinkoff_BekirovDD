package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        int a = 11211230;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(true);
    }
    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        int a = 13001120;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(true);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        int a = 23336014;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(true);
    }

    @Test
    @DisplayName("Тест4")
    void test4() {
        //given
        int a = 11;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(true);
    }

    @Test
    @DisplayName("Тест5")
    void test5() {
        //given
        int a = 23;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(false);
    }

    @Test
    @DisplayName("Тест6")
    void test6() {
        //given
        int a = 123123123;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(false);
    }

    @Test
    @DisplayName("Тест7")
    void test7() {
        //given
        int a = 1;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(false);
    }

    @Test
    @DisplayName("Тест8")
    void test8() {
        //given
        int a = -11;

        //when
        boolean ret = Task5.isPalindromeDescendant(a);

        //then
        assertThat(ret).isEqualTo(true);
    }
}
