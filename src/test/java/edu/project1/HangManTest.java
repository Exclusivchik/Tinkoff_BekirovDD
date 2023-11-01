package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangManTest {
    @Test
    @DisplayName("Поражение")
    void test1() {
        // given
        String hiddenWord = RandomWord.get();
        while (hiddenWord.isEmpty()) {
            hiddenWord = RandomWord.get();
        }
        String alph = "qwertyuiopasdfghjklzxcvbnm";
        String badLetters = "";
        for (int i = 0; i < 26; i++) {
            if (!hiddenWord.contains("" + alph.charAt(i))) {
                badLetters = badLetters + alph.charAt(i) + '\n';
            }
        }
        HangMan game = new HangMan(badLetters, hiddenWord);

        // when
        String result = game.run();
        // then
        assertThat(result).isEqualTo("LOSE");
    }

    @Test
    @DisplayName("Некорректная длина слова")
    void test2() {
        // given
        String hiddenWord = "";
        HangMan game = new HangMan("asdas", hiddenWord);
        // when
        String result = game.run();
        // then
        assertThat(result).isEqualTo("INCORRECT");
    }

    @Test
    @DisplayName("Изменение состояния игры")
    void test3() {
        // given
        String hiddenWord = "hello";
        HangMan game = new HangMan("q\nh\nt\ne\nl\nl\nigorgoffman\nw\no", hiddenWord);
        game.run();
        // when
        int[] state = game.getState();
        // then
        assertThat(state).isEqualTo(new int[]{5, 3, 1, 1});
    }

    @Test
    @DisplayName("Проверка на опечатку")
    void test4() {
        // given
        String hiddenWord = "hello";
        HangMan game = new HangMan("igorgoffman\n7-8odejdu\nspartak\nh\ne\nl\no", hiddenWord);
        game.run();
        // when
        int[] state = game.getState();
        // then
        assertThat(state).isEqualTo(new int[]{5, 0, 3, 0});
    }
}
