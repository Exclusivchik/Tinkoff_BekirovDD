package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        List<String> list = List.of("a", "bb", "a", "bb");
        //when
        var freq = Task3.freqDict(list);
        //then
        Map<String, Integer> finMap = new HashMap<>();
        finMap.put("bb", 2);
        finMap.put("a", 2);

        assertThat(freq).isEqualTo(finMap);
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        List<String> list = List.of("this", "and", "that", "and");
        //when
        var freq = Task3.freqDict(list);
        //then
        Map<String, Integer> finMap = new HashMap<>();
        finMap.put("that", 1);
        finMap.put("and", 2);
        finMap.put("this", 1);
        assertThat(freq).isEqualTo(finMap);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        List<String> list = List.of("код", "код", "код", "bug");
        //when
        var freq = Task3.freqDict(list);
        //then
        Map<String, Integer> finMap = new HashMap<>();
        finMap.put("код", 3);
        finMap.put("bug", 1);
        assertThat(freq).isEqualTo(finMap);
    }

    @Test
    @DisplayName("Тест4")
    void test4() {
        //given
        List<Integer> list = List.of(1, 1, 2, 2);
        //when
        var freq = Task3.freqDict(list);
        //then
        Map<Integer, Integer> finMap = new HashMap<>();
        finMap.put(1, 2);
        finMap.put(2, 2);
        assertThat(freq).isEqualTo(finMap);
    }
}
