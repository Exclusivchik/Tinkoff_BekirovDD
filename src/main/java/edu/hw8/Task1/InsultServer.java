package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsultServer implements AutoCloseable {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final static Logger LOGGER = LogManager.getLogger();
    private final int port;
    private boolean closed = false;

    public InsultServer(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!closed) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Клиент Пришёл");
                pool.execute(new InsultHandler(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        closed = true;
        new Client(port).send("end");
    }
}
