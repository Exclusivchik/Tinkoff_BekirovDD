package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        // given
        String s = "Hello world!";

        // when
        String encrypted = Task1.atbash(s);

        // then
        assertThat(encrypted).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        // given
        String s = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";

        // when
        String encrypted = Task1.atbash(s);

        // then
        assertThat(encrypted).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }
}
