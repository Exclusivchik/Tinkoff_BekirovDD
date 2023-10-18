package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        String s = "123456";

        //when
        String ret = Task4.fixString(s);

        //then
        assertThat(ret).isEqualTo("214365");
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        String s = "hTsii  s aimex dpus rtni.g";

        //when
        String ret = Task4.fixString(s);

        //then
        assertThat(ret).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        String s = "badce";

        //when
        String ret = Task4.fixString(s);

        //then
        assertThat(ret).isEqualTo("abcde");
    }
}
