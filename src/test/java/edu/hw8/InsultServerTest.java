package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.InsultServer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InsultServerTest {
    private static final Map<String, String> REQUEST_TO_RESPONSE = Map.of(
        "личности", "Не переходи на личности там, где их нет.",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления."
    );
    private static final String DEFAULT_RESPONSE =
        "Я не понимаю, что ты сейчас говоришь: я не говорю на абсурдном языке.";

    @Test
    @DisplayName("Правильность работы")
    void test() throws InterruptedException {
        InsultServer insultServer = new InsultServer(4444);
        Thread runServerThread = new Thread(insultServer::run);
        Thread closeServerThread = new Thread(insultServer::close);
        var responses = Collections.synchronizedCollection(new ArrayList<Pair>());
        List<String> messages = List.of(
            "личности", "оскорбления", "глупый", "интеллект", "Игорь Гоффман"
        );

        List<Runnable> requests = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String message = messages.get(i);
            Runnable request = () -> responses.add(
                new Pair(message, new Client(4444).send(message))
            );
            requests.add(request);
        }
        runServerThread.start();
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            for (var request : requests) {
                executorService.submit(request);
            }
        }
        closeServerThread.start();
        runServerThread.join();
        closeServerThread.join();

        for (var temp : responses) {
            String req = temp.s1();
            String res = temp.s2();
            Assertions.assertEquals(REQUEST_TO_RESPONSE.getOrDefault(req, DEFAULT_RESPONSE), res);
        }
        Assertions.assertEquals(5, responses.size());
    }
}
