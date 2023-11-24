package edu.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticCollector {
    private static final int MAX_ADDR = 10;
    private static final Map<Integer, String> CODES = Map.of(
        304, "Not Modified",
        416, "Range Not Satisfiable",
        403, "Forbidden",
        200, "OK",
        206, "Partial Content",
        404, "Not Found"
    );

    private final List<NginxLog> logs;
    private final String from;
    private final String to;


    StatisticCollector(List<NginxLog> logs, String from, String to) {
        this.logs = logs;
        this.from = from;
        this.to = to;
    }

    private long getMeanRequestSize() {
        if (!logs.isEmpty()) {
            return logs.stream().mapToLong(NginxLog::getBodyBytesSent).sum() / logs.size();
        }
        return 0;
    }

    private Map<Integer, Integer> getMapOfCodes() {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (NginxLog log : logs) {
            cnt.put(log.getStatus(), cnt.getOrDefault(log.getStatus(), 0) + 1);
        }
        return cnt;
    }

    private Map<String, Integer> getResources() {
        Map<String, Integer> resources = new HashMap<>();
        for (NginxLog log : logs) {
            String resource = log.getRequest().split(" ")[1];
            resources.put(resource, resources.getOrDefault(resource, 0) + 1);
        }
        return resources;
    }

    private String getOrigin() {
        Set<String> origin = new HashSet<>();
        for (NginxLog log : logs) {
            origin.add(log.getOrigin());
        }
        StringBuilder ret = new StringBuilder();
        for (var path : origin) {
            ret.append("\'").append(path).append("\' ");
        }
        return ret.toString();
    }

    private List<Map.Entry<String, Long>> getTopNRemoteAddrs() {
        Map<String, Long> addressFrequencyMap =
            logs.stream()
                .map(NginxLog::getRemoteAddr)
                .collect(Collectors.groupingBy(address -> address, Collectors.counting()));

        return addressFrequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(MAX_ADDR).toList();

    }

    private List<Map.Entry<String, Long>> getTopNLocalDates() {
        Map<String, Long> dateFrequencyMap =
            logs.stream()
                .map(NginxLog::getStringDate)
                .collect(Collectors.groupingBy(address -> address, Collectors.counting()));

        return dateFrequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(MAX_ADDR).toList();

    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getGeneralInfoMd() {
        StringBuilder info = new StringBuilder();
        info.append("#### Общая информация\n\n");
        info.append("|Метрика|Значение|\n").append("|:-:|:-:|\n");
        info.append("|Ресурсы|").append(getOrigin()).append("|\n");
        info.append("|Начальная дата|").append(from == null ? "-" : from).append("|\n");
        info.append("|Конечная дата|").append(to == null ? "-" : to).append("|\n");
        info.append("|Количество запросов|").append(logs.size()).append("|\n");
        info.append("|Средний размер запроса|").append(getMeanRequestSize()).append("b|\n\n");
        return info.toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getRequestResourcesMd() {
        StringBuilder info = new StringBuilder();
        info.append("#### Запрашиваемые ресурсы\n\n");
        info.append("|Ресурс|Количество|\n").append("|:-:|:-:|\n");
        for (var entry : getResources().entrySet()) {
            info.append("|").append(entry.getKey()).append("|").append(entry.getValue()).append("|\n");
        }
        return info.toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getCodesMd() {
        StringBuilder info = new StringBuilder();
        info.append("#### Коды ответа\n\n");
        info.append("|Код|Имя|Количество|\n").append("|:-:|:-:|:-:|\n");
        for (var entry : getMapOfCodes().entrySet()) {
            info.append("|").append(entry.getKey())
                .append("|").append(CODES.get(entry.getKey()))
                .append("|").append(entry.getValue()).append("|\n");
        }
        return info.toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getTop10AddrsMd() {
        StringBuilder info = new StringBuilder();
        info.append("#### Топ 10 адресов\n\n");
        info.append("|Адрес|Количество запросов|\n").append("|:-:|:-:|\n");
        for (var entry : getTopNRemoteAddrs()) {
            info.append("|").append(entry.getKey())
                .append("|").append(entry.getValue()).append("|\n");
        }
        return info.toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getTop10DatesMd() {
        StringBuilder info = new StringBuilder();
        info.append("#### Топ 10 дат\n\n");
        info.append("|Адрес|Количество логов|\n").append("|:-:|:-:|\n");
        for (var entry : getTopNLocalDates()) {
            info.append("|").append(entry.getKey())
                .append("|").append(entry.getValue()).append("|\n");
        }
        return info.toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getGeneralInfoAdoc() {
        StringBuilder info = new StringBuilder();
        info.append("==== Общая информация\n");
        info.append("[width=\"100%\",options=\"header\", cols=\"^,^\"]\n");
        info.append("|====================\n");
        info.append("|Метрика|Значение\n");
        info.append("|Ресурсы|").append(getOrigin()).append("\n");
        info.append("|Начальная дата|").append(from == null ? "-" : from).append("\n");
        info.append("|Конечная дата|").append(to == null ? "-" : to).append("\n");
        info.append("|Количество запросов|").append(logs.size()).append("\n");
        info.append("|Средний размер запроса|").append(getMeanRequestSize()).append("b\n");
        info.append("|====================\n");
        return info.toString();
    }

    private String getRequestResourcesAdoc() {
        StringBuilder info = new StringBuilder();
        info.append("==== Запрашиваемые ресурсы\n");
        info.append("[width=\"100%\",options=\"header\", cols=\"^,^\"]\n");
        info.append("|====================\n");
        info.append("|Ресурс|Количество\n");
        for (var entry : getResources().entrySet()) {
            info.append("|").append(entry.getKey()).append("|").append(entry.getValue()).append("\n");
        }
        info.append("|====================\n");
        return info.toString();
    }

    private String getCodesAdoc() {
        StringBuilder info = new StringBuilder();
        info.append("==== Коды ответа\n\n");
        info.append("[width=\"100%\",options=\"header\", cols=\"<,^,>\"]\n");
        info.append("|====================\n");
        info.append("|Код|Имя|Количество\n");
        for (var entry : getMapOfCodes().entrySet()) {
            info.append("|").append(entry.getKey())
                .append("|").append(CODES.get(entry.getKey()))
                .append("|").append(entry.getValue()).append("\n");
        }
        info.append("|====================\n");
        return info.toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private String getTop10AddrsAdoc() {
        StringBuilder info = new StringBuilder();
        info.append("==== Топ 10 адресов\n\n");
        info.append("[width=\"100%\",options=\"header\", cols=\"<,^\"]\n");
        info.append("|====================\n");
        info.append("|Адрес|Количество запросов\n");
        for (var entry : getTopNRemoteAddrs()) {
            info.append("|").append(entry.getKey())
                .append("|").append(entry.getValue()).append("\n");
        }
        info.append("|====================\n");
        return info.toString();
    }

    private String getTop10DatesAdoc() {
        StringBuilder info = new StringBuilder();
        info.append("==== Топ 10 Дат\n\n");
        info.append("[width=\"100%\",options=\"header\", cols=\"<,^\"]\n");
        info.append("|====================\n");
        info.append("|Дата|Количество логов\n");
        for (var entry : getTopNLocalDates()) {
            info.append("|").append(entry.getKey())
                .append("|").append(entry.getValue()).append("\n");
        }
        info.append("|====================\n");
        return info.toString();
    }

    public void createMarkdownFile() {
        File file = new File("pretty-logs.md");
        try (FileWriter fileWriter = new FileWriter(file); BufferedWriter writer = new BufferedWriter(fileWriter)) {

            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) {
                    throw new RuntimeException();
                }
            }
            writer.write(getGeneralInfoMd());
            writer.write(getRequestResourcesMd());
            writer.write(getCodesMd());
            writer.write(getTop10AddrsMd());
            writer.write(getTop10DatesMd());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void createAdocFile() {
        File file = new File("pretty-logs.adoc");
        try (FileWriter fileWriter = new FileWriter(file); BufferedWriter writer = new BufferedWriter(fileWriter)) {

            if (!file.exists()) {
                boolean flag = file.createNewFile();
                if (!flag) {
                    throw new RuntimeException();
                }
            }
            writer.write(getGeneralInfoAdoc());
            writer.write(getRequestResourcesAdoc());
            writer.write(getCodesAdoc());
            writer.write(getTop10AddrsAdoc());
            writer.write(getTop10DatesAdoc());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
