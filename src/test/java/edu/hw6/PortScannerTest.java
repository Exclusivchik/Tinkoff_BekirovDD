package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PortScannerTest {
    @Test
    @DisplayName("getUPDSocketStatus")
    void test1() {
        String expected = "NetBIOS Name Service";
        String actual = PortScanner.getUPDSocketStatus(137);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getTCPSocketStatus")
    void test2() {
        String expected = "NetBIOS Session Service";
        String actual = PortScanner.getTCPSocketStatus(139);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getUPDSocketStatus")
    void test3() {
        String expected = "Free";
        String actual = PortScanner.getUPDSocketStatus(7878);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getTCPSocketStatus")
    void test4() {
        String expected = "Free";
        String actual = PortScanner.getTCPSocketStatus(7878);
        Assertions.assertEquals(expected, actual);
    }
}
