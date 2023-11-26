package edu.hw7;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

public final class FactorialCalculator {
    private FactorialCalculator() {
    }

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        List<Integer> list = IntStream.rangeClosed(2, n).boxed().toList();
        return list.parallelStream().map(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
