package edu.hw3;

import edu.hw3.Task7.PermitsNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        TreeMap<String, String> tree = new TreeMap<>(new PermitsNull<>());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
