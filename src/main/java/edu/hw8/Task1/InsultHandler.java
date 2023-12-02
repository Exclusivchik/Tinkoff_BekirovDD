package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

class InsultHandler implements Runnable {
    private static final Map<String, String> REQUEST_TO_RESPONSE = Map.of(
        "личности", "Не переходи на личности там, где их нет.",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления."
    );
    private static final String DEFAULT_RESPONSE =
        "Я не понимаю, что ты сейчас говоришь: я не говорю на абсурдном языке.";
    private final Socket socket;

    InsultHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String response = getInsult(inputLine);
                out.println(response);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private String getInsult(String input) {
        return REQUEST_TO_RESPONSE.getOrDefault(input, DEFAULT_RESPONSE);
    }
}
