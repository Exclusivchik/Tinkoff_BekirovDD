package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public final class LogsParser {
    private LogsParser() {
    }

    public static List<NginxLog> parseFromURL(String urlPath, OffsetDateTime from, OffsetDateTime to) {
        List<NginxLog> logList = new ArrayList<>();
        try {
            URL url = new URL(urlPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    NginxLog log = new NginxLog(line, urlPath.substring(urlPath.lastIndexOf("/")));
                    if (from.isBefore(log.getTimeLocal()) && log.getTimeLocal().isBefore(to)) {
                        logList.add(log);
                    }
                } catch (IllegalArgumentException e) {
                }
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return logList;
    }

    public static List<NginxLog> parseFromFiles(List<Path> paths, OffsetDateTime from, OffsetDateTime to) {
        List<NginxLog> logList = new ArrayList<>();
        for (Path path : paths) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    try {
                        NginxLog log = new NginxLog(line, path.getFileName().toString());
                        if (from.isBefore(log.getTimeLocal()) && log.getTimeLocal().isBefore(to)) {
                            logList.add(log);
                        }
                    } catch (IllegalArgumentException e) {
                    }
                }
            } catch (IOException e) {
            }
        }
        return logList;
    }

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:RegexpSinglelineJava", "checkstyle:UncommentedMain"})
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        OffsetDateTime from = OffsetDateTime.of(2011, 11, 11, 11, 11, 11, 11, ZoneOffset.of("+00:00"));
        OffsetDateTime to = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+00:00"));
        List<NginxLog> parsedLogs = parseFromFiles(List.of(Path.of("logs_for_project3/log1.log")), from, to);

        // Выводим информацию о первых 10 записях
        for (int i = 0; i < 10 && i < parsedLogs.size(); i++) {
            NginxLog log = parsedLogs.get(i);
            System.out.println("Адрес: " + log.getRemoteAddr());
            System.out.println("Время: " + log.getTimeLocal());
            System.out.println("Метод запроса: " + log.getRequest());
            System.out.println("Код ответа: " + log.getStatus());
            System.out.println("--------------------------------------------");
        }
    }
}
