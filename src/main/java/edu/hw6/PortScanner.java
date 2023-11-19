package edu.hw6;

import java.net.BindException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("checkstyle:MagicNumber")
public final class PortScanner {
    private static final String FREE = "Free";
    private static final String UNKNOWN = "Unknown";

    private PortScanner() {
    }

    private static final Map<Integer, String> COMMON_PORTS = new HashMap<>() {{
        put(135, "EPMAP");
        put(137, "NetBIOS Name Service");
        put(138, "NetBIOS Datagram Service");
        put(139, "NetBIOS Session Service");
        put(445, "Microsoft-DS Active Directory");
        put(843, "Adobe Flash");
        put(1900, "SSDP");
    }};

    public static String getTCPSocketStatus(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return FREE;
        } catch (Exception e) {
            if (e instanceof BindException) {
                return COMMON_PORTS.getOrDefault(port, UNKNOWN);
            }
        }
        return "";
    }

    public static String getUPDSocketStatus(int port) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();
            return FREE;
        } catch (Exception e) {
            if (e instanceof BindException) {
                return COMMON_PORTS.getOrDefault(port, UNKNOWN);
            }
        }
        return "";
    }
}
