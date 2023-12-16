package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class Task2Test {
    @Test
    void test() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<? extends ArithmeticUtils> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(new DelegatorClass()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        var ctor = dynamicType.getConstructors()[0];
        var instance = (ArithmeticUtils) ctor.newInstance();
        Assertions.assertEquals(78, instance.sum(39, 2));
    }
}
