package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> freqMap = new HashMap<>();

        for (T item : list) {
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
        }

        return freqMap;
    }
}
