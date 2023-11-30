package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class FactorialCalculatorTest {
    @Test
    void test1() {
        Assertions.assertEquals(BigInteger.valueOf(24), FactorialCalculator.factorial(4));
    }

    @Test
    void test2() {
        Assertions.assertEquals(BigInteger.valueOf(1), FactorialCalculator.factorial(0));
    }

    @Test
    void test3() {
        Assertions.assertThrows(RuntimeException.class, () -> FactorialCalculator.factorial(-1));
    }
}
