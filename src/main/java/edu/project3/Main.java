package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class Main {
    private Main() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        List<String> paths = new ArrayList<>();
        String from = null;
        String to = null;
        String format = "markdown";
        int iter = 0;
        int last = 0;

        while (iter != args.length) {
            if ("--format".equals(args[iter])) {
                iter++;
                format = args[iter];
                iter++;
                last = 0;
            } else if ("--from".equals(args[iter])) {
                iter++;
                from = args[iter];
                iter++;
                last = 0;
            } else if ("--to".equals(args[iter])) {
                iter++;
                to = args[iter++];
                last = 0;
            } else if ("--path".equals(args[iter])) {
                last = 1;
                iter++;
            }
            if (last == 1) {
                paths.add(args[iter]);
                iter++;
            }
        }
        OffsetDateTime correctFrom = null;
        OffsetDateTime correctTo = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        if (from != null) {
            correctFrom = OffsetDateTime
                .of(LocalDateTime.of(LocalDate.parse(from, formatter), LocalTime.MIN), ZoneOffset.UTC);
        }
        if (to != null) {
            correctTo = OffsetDateTime
                .of(LocalDateTime.of(LocalDate.parse(to, formatter), LocalTime.MIN), ZoneOffset.UTC);
        }
        List<NginxLog> fullLogs = new ArrayList<>();
        for (var path : paths) {
            try {
                List<NginxLog> tempLogs = LogsParser.parseFromFile(path, correctFrom, correctTo);
                fullLogs.addAll(tempLogs);
            } catch (RuntimeException e) {
                try {
                    List<NginxLog> tempLogs = LogsParser.parseFromURL(path, correctFrom, correctTo);
                    fullLogs.addAll(tempLogs);
                } catch (RuntimeException t) {
                }
            }
        }
        StatisticCollector statisticCollector = new StatisticCollector(fullLogs, from, to);
        if ("adoc".equals(format)) {
            statisticCollector.createAdocFile();
        } else {
            statisticCollector.createMarkdownFile();
        }
    }
}
