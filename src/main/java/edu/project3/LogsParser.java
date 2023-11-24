package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
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
                    if ((from  == null || from.isBefore(log.getDateTimeLocal()))
                        && (to == null || log.getDateTimeLocal().isBefore(to))) {
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

    public static List<NginxLog> parseFromFile(String path, OffsetDateTime from, OffsetDateTime to) {
        List<NginxLog> logList = new ArrayList<>();
        try {
            Path correctPath = Path.of(path);
            List<String> lines = Files.readAllLines(correctPath);
            for (String line : lines) {
                try {
                    NginxLog log = new NginxLog(line, correctPath.getFileName().toString());
                    if ((from  == null || from.isBefore(log.getDateTimeLocal()))
                        && (to == null || log.getDateTimeLocal().isBefore(to))) {
                        logList.add(log);
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return logList;
    }
}
