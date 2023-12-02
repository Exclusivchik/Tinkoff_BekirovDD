package edu.hw8.Task3;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class PasswordBruteforcer {
    private static final char[] ALPH = "abcdefghigklmnopqrstuvwxyz1234567890".toCharArray();
    private static final int POWER = 36;

    private PasswordBruteforcer() {
    }

    public static String nextPassword(int num, int len) {
        int tempNum = num;
        String password = "";
        for (int i = 0; i < len; i++) {
            char tempSym = ALPH[tempNum / (int) Math.pow(POWER, len - i - 1)];
            password += tempSym;
            tempNum %= (int) Math.pow(POWER, len - i - 1);
        }
        return password;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static String getMD5(String input) {
        try {

            byte[] messageDigest = MessageDigest.getInstance("MD5").digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> multiThreadBruteforce(Map<String, String> map, int passwordLen) {
        ConcurrentMap<String, String> founded = new ConcurrentHashMap<>();

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        long passwordCount = (long) Math.pow(POWER, passwordLen);
        long perThread = passwordCount / threads;

        AtomicInteger mapSize = new AtomicInteger(map.size());

        for (int i = 0; i < threads; i++) {
            long iterations = perThread;
            if (i == threads - 1) {
                iterations += passwordCount % threads;
            }
            long finalIterations = iterations;
            int finalI = i;
            executor.submit(() -> {
                for (int j = 0; j < finalIterations; j++) {
                    String tempPassword = nextPassword((int) (finalI * perThread + j), passwordLen);
                    String hashedPassword = getMD5(tempPassword);
                    if (map.containsKey(hashedPassword)) {
                        founded.put(tempPassword, map.get(hashedPassword));
                        if (mapSize.decrementAndGet() == 0) {
                            executor.shutdownNow();
                        }
                    }
                }
            });
        }
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        return founded;
    }

    public static Map<String, String> linearBruteforce(Map<String, String> map, int passwordLen) {
        Map<String, String> founded = new HashMap<>();
        int mapSize = map.size();
        long passwordCount = (long) Math.pow(POWER, passwordLen);
        for (int i = 0; i < passwordCount; i++) {
            String tempPassword = nextPassword(i, passwordLen);
            String hashedPassword = getMD5(tempPassword);
            if (map.containsKey(hashedPassword)) {
                founded.put(tempPassword, map.get(hashedPassword));
                mapSize--;
            }
            if (mapSize == 0) {
                return founded;
            }
        }
        return founded;
    }
}
