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

    public static void main(String[] args) {
        List<String> list1 = List.of("a", "bb", "a", "bb");
        System.out.println(freqDict(list1)); // Output: {bb=2, a=2}

        List<String> list2 = List.of("this", "and", "that", "and");
        System.out.println(freqDict(list2)); // Output: {that=1, and=2, this=1}

        List<String> list3 = List.of("код", "код", "код", "bug");
        System.out.println(freqDict(list3)); // Output: {код=3, bug=1}

        List<Integer> list4 = List.of(1, 1, 2, 2);
        System.out.println(freqDict(list4)); // Output: {1=2, 2=2}
    }
}
