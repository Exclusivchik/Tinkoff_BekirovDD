package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    @DisplayName("test1")
    void test1() {
        String needle = "abc";
        String haystack = "achfdbaabgabcaabg";
        Assertions.assertTrue(Task6.isSubsequence(needle, haystack));
    }

    @Test
    @DisplayName("test2")
    void test2() {
        String needle = "abcd";
        String haystack = "achfdbaabgabcaabg";
        Assertions.assertFalse(Task6.isSubsequence(needle, haystack));
    }

    @Test
    @DisplayName("test3")
    void test3() {
        String needle = "igorgoffman";
        String haystack = "idssgqwewjefojdsfdvjrkcsvjhjgaskdjdokdjsvfdfasdnfasmsnmkasdsjdaadsvndm";
        Assertions.assertTrue(Task6.isSubsequence(needle, haystack));
    }
}
