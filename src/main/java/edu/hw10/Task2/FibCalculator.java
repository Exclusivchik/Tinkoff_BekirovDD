package edu.hw10.Task2;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
