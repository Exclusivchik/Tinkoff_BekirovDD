package edu.hw3;


import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Test1")
    void test1() {
        //given
        var backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        //when
        int tempElem = 3;
        while (backwardIterator.hasNext()) {
            //then
            assertThat(backwardIterator.next()).isEqualTo(tempElem);
            tempElem--;
        }
    }

    @Test
    @DisplayName("Test2")
    void test2() {
        //given
        Map<Integer, String> stringStringMap = new TreeMap<>();
        stringStringMap.put(1, "1");
        stringStringMap.put(2, "2");
        stringStringMap.put(3, "3");
        stringStringMap.put(4, "3");
        stringStringMap.put(5, "3");
        var backwardIterator = new BackwardIterator<>(stringStringMap.keySet());
        //when
        int tempElem = 5;
        while (backwardIterator.hasNext()) {
            //then
            assertThat(backwardIterator.next()).isEqualTo(tempElem);
            tempElem--;
        }
    }
}
