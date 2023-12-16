package edu.hw11;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw11.Task3.createClass;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    void shouldReturnCorrectFibonacciNumbers()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Long> result = new ArrayList<>();

        Class<?> fibClass = createClass();
        Method fib = fibClass.getMethod("fib", int.class);
        for (int i = 0; i <= 7; i++) {
            result.add((Long) fib.invoke(null, i));
        }

        assertThat(result)
            .containsExactly(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L);
    }
}
