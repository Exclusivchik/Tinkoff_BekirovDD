package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        String seq = "()()()";
        //when
        var clusters = Task2.clusterize(seq);
        //then
        List<String> reqClusters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            reqClusters.add("()");
        }
        assertThat(clusters).isEqualTo(reqClusters);
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        String seq = "((()))";
        //when
        var clusters = Task2.clusterize(seq);
        //then
        List<String> reqClusters = new ArrayList<>();
        reqClusters.add("((()))");
        assertThat(clusters).isEqualTo(reqClusters);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        String seq = "((()))(())()()(()())";
        //when
        var clusters = Task2.clusterize(seq);
        //then
        List<String> reqClusters = new ArrayList<>();
        reqClusters.add("((()))");
        reqClusters.add("(())");
        reqClusters.add("()");
        reqClusters.add("()");
        reqClusters.add("(()())");
        assertThat(clusters).isEqualTo(reqClusters);
    }

    @Test
    @DisplayName("Тест4")
    void test4() {
        //given
        String seq = "((())())(()(()()))";
        //when
        var clusters = Task2.clusterize(seq);
        //then
        List<String> reqClusters = new ArrayList<>();
        reqClusters.add("((())())");
        reqClusters.add("(()(()()))");
        assertThat(clusters).isEqualTo(reqClusters);
    }

    @Test
    @DisplayName("Тест5")
    void test5() {
        //given
        String seq = "))((";
        //when
        var clusters = Task2.clusterize(seq);
        //then
        assertThat(clusters).isEqualTo(new ArrayList<>());
    }

    @Test
    @DisplayName("Тест6")
    void test6() {
        //given
        String seq = "()()(f";
        //when
        var clusters = Task2.clusterize(seq);
        //then
        assertThat(clusters).isEqualTo(new ArrayList<>());
    }
}
