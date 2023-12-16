package edu.hw10.Task1;

import edu.hw10.Task1.Annots.Max;
import edu.hw10.Task1.Annots.Min;
import edu.hw10.Task1.Annots.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomObjectGenerator {
    private static final String DEFAULT_STRING = "Igor Goffman";

    private RandomObjectGenerator() {
    }

    public static Object nextObject(Class<?> requiredClass, String fabricMethodName) {
        try {
            Method fabricMethod = findFabricMethod(requiredClass, fabricMethodName);
            Parameter[] params = fabricMethod.getParameters();
            if (params.length == 0) {
                return fabricMethod.invoke(null);
            }
            return createByFabricMethod(fabricMethod, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object nextObject(Class<?> requiredClass) {
        Constructor<?> constructor = requiredClass.getConstructors()[0];
        Parameter[] params = constructor.getParameters();
        try {
            if (params.length == 0) {
                return constructor.newInstance();
            }
            return createByConstructor(constructor, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object createByFabricMethod(Method fabricMethod, Parameter[] params)
        throws InvocationTargetException, IllegalAccessException {
        Object[] createdParams = createParams(params);
        return fabricMethod.invoke(null, createdParams);
    }

    private static Object createByConstructor(Constructor<?> constructor, Parameter[] params)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object[] createdParams = createParams(params);
        return constructor.newInstance(createdParams);
    }

    private static Object[] createParams(Parameter[] params) {
        Object[] createdParams = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            var paramType = params[i].getType();
            var annotationsForParam = params[i].getAnnotations();
            if (paramType.equals(String.class)) {
                if (annotationsForParam.length == 0) {
                    createdParams[i] = null;
                } else {
                    for (Annotation annotation : annotationsForParam) {
                        if (annotation.annotationType().equals(NotNull.class)) {
                            createdParams[i] = DEFAULT_STRING;
                        }
                    }
                }
            } else if (paramType.equals(Integer.class) || paramType.equals(int.class)) {
                if (annotationsForParam.length == 0) {
                    createdParams[i] = 0;
                } else {
                    int min = Integer.MIN_VALUE;
                    int max = Integer.MAX_VALUE;
                    for (Annotation annotation : annotationsForParam) {
                        if (annotation.annotationType().equals(Min.class)) {
                            min = ((Min) annotation).value();
                        }
                        if (annotation.annotationType().equals(Max.class)) {
                            max = ((Max) annotation).value();
                        }
                    }
                    createdParams[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
                }
            }
        }
        return createdParams;
    }

    private static Method findFabricMethod(Class<?> clazz, String fabricMethodName) throws NoSuchMethodException {
        Method[] methodList = clazz.getMethods();
        for (Method method : methodList) {
            if (fabricMethodName.equals(method.getName())) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }
}
