package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        int num = 78;
        //when
        var roman = Task4.toRoman(num);
        //then
        assertThat(roman).isEqualTo("LXXVIII");
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        int num = 52;
        //when
        var roman = Task4.toRoman(num);
        //then
        assertThat(roman).isEqualTo("LII");
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        int num = 3999;
        //when
        var roman = Task4.toRoman(num);
        //then
        assertThat(roman).isEqualTo("MMMCMXCIX");
    }

    @Test
    @DisplayName("Тест4")
    void test4() {
        //given
        int num = -2;
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.toRoman(num));
    }

    @Test
    @DisplayName("Тест5")
    void test5() {
        //given
        int num = 4000;
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.toRoman(num));
    }
}
