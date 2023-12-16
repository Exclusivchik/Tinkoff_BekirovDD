package edu.hw10.Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class CacheProxy implements InvocationHandler {
    private static final Map<String, Object> CACHE_MAP = new HashMap<>();
    private final Object target;

    private CacheProxy(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var cache = method.getAnnotation(Cache.class);
        if (cache != null) {
            Object result;
            String key = method.getName() + " " + Arrays.toString(args);
            if (cache.persist()) {
                File fileToSave = new File("src/main/java/edu/hw10/task2/cache/" + key + ".txt");
                if (fileToSave.exists()) {
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileToSave))) {
                        result = in.readObject();
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                } else {
                    result = method.invoke(target, args);
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
                        out.writeObject(result);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }
            } else if (CACHE_MAP.containsKey(key)) {
                result = CACHE_MAP.get(key);
            } else {
                result = method.invoke(target, args);
                CACHE_MAP.put(key, result);
            }
            return result;
        }
        return method.invoke(target, args);
    }
}
