package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.Task6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Assertions;

public class Task6Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        Task6 stockMarket = new Task6();
        //when
        stockMarket.add(new Stock(5));
        stockMarket.add(new Stock(4));
        stockMarket.add(new Stock(6));
        //then
        assertThat(stockMarket.mostValuableStock()).isEqualTo(new Stock(6));
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        Task6 stockMarket = new Task6();
        //when

        //then
        assertThat(stockMarket.mostValuableStock()).isEqualTo(null);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        Task6 stockMarket = new Task6();
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> stockMarket.remove(new Stock(0)));
    }
}
