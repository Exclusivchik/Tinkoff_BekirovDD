package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final int portNumber;
    private final static Logger LOGGER = LogManager.getLogger();

    public Client(int portNumber) {
        this.portNumber = portNumber;
    }

    public String send(String message) {
        String hostName = "localhost";
        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(message);
            String response = in.readLine();
            LOGGER.info("Server: " + response);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
