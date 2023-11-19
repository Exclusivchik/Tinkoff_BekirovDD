package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HackerNewsTest {
    @Test
    @DisplayName("hackerNewsTopStories")
    void test1() {
        HackerNews hn = new HackerNews();
        long[] topStories = hn.hackerNewsTopStories();
        Assertions.assertNotEquals(new long[0], topStories);
    }

    @Test
    @DisplayName("news")
    void test2() {
        HackerNews hn = new HackerNews();
        String newsTitle = hn.news(3757123);;
        String expected = "Mike Daisey Apologizes for Falsehoods in Monologue About Apple";
        Assertions.assertEquals(expected, newsTitle);
    }

    @Test
    @DisplayName("newsThrows")
    void test3() {
        HackerNews hn = new HackerNews();
        Assertions.assertThrows(RuntimeException.class, () -> hn.news(-100000));
    }
}
