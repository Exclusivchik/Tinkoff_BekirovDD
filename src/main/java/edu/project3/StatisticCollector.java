package edu.project3;

import java.util.List;

public class StatisticCollector {
    private StatisticCollector() {
    }

    private static List<NginxLog> logs;

    StatisticCollector(List<NginxLog> logs) {
        this.logs = logs;
    }

    public static void createMarkdownFile() {
    }

    public static void createAdocFile() {
    }
}
