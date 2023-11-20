package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StatisticCollectorTest {
    @Test
    void markdown() throws IOException {
        String url =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        var logs = LogsParser.parseFromURL(url, null, null);
        StatisticCollector statisticCollector = new StatisticCollector(logs, "-", "-");
        statisticCollector.createMarkdownFile();
        Path firstFile = Path.of("pretty-logs.md");
        String required = "#### Общая информация\n" +
            "\n" +
            "|Метрика|Значение|\n" +
            "|:-:|:-:|\n" +
            "|Ресурсы|'/nginx_logs' |\n" +
            "|Начальная дата|-|\n" +
            "|Конечная дата|-|\n" +
            "|Количество запросов|51462|\n" +
            "|Средний размер запроса|659509b|\n" +
            "\n" +
            "#### Запрашиваемые ресурсы\n" +
            "\n" +
            "|Ресурс|Количество|\n" +
            "|:-:|:-:|\n" +
            "|/downloads/product_3|73|\n" +
            "|/downloads/product_1|30285|\n" +
            "|/downloads/product_2|21104|\n" +
            "#### Коды ответа\n" +
            "\n" +
            "|Код|Имя|Количество|\n" +
            "|:-:|:-:|:-:|\n" +
            "|304|Not Modified|13330|\n" +
            "|416|Range Not Satisfiable|4|\n" +
            "|403|Forbidden|38|\n" +
            "|404|Not Found|33876|\n" +
            "|200|OK|4028|\n" +
            "|206|Partial Content|186|\n" +
            "#### Топ 10 адресов\n" +
            "\n" +
            "|Адрес|Количество запросов|\n" +
            "|:-:|:-:|\n" +
            "|216.46.173.126|2350|\n" +
            "|180.179.174.219|1720|\n" +
            "|204.77.168.241|1439|\n" +
            "|65.39.197.164|1365|\n" +
            "|80.91.33.133|1202|\n" +
            "|84.208.15.12|1120|\n" +
            "|74.125.60.158|1084|\n" +
            "|119.252.76.162|1064|\n" +
            "|79.136.114.202|628|\n" +
            "|54.207.57.55|532|\n" +
            "#### Топ 10 дат\n" +
            "\n" +
            "|Адрес|Количество логов|\n" +
            "|:-:|:-:|\n" +
            "|23/MAY/2015|2892|\n" +
            "|27/MAY/2015|2887|\n" +
            "|21/MAY/2015|2881|\n" +
            "|26/MAY/2015|2879|\n" +
            "|22/MAY/2015|2879|\n" +
            "|30/MAY/2015|2876|\n" +
            "|2/JUNE/2015|2864|\n" +
            "|31/MAY/2015|2863|\n" +
            "|18/MAY/2015|2855|\n" +
            "|24/MAY/2015|2853|\n";
        String response = "";
        try {
            try (BufferedReader bf1 = Files.newBufferedReader(firstFile)) {

                String line;
                while ((line = bf1.readLine()) != null) {
                    response += line + '\n';
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(required, response);
        Files.deleteIfExists(Path.of("pretty-logs.md"));
    }

    @Test
    void adoc() throws IOException {
        String url =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        var logs = LogsParser.parseFromURL(url, null, null);
        StatisticCollector statisticCollector = new StatisticCollector(logs, "-", "-");
        statisticCollector.createAdocFile();
        Path firstFile = Path.of("pretty-logs.adoc");
        String required = "==== Общая информация\n" +
            "[width=\"100%\",options=\"header\", cols=\"^,^\"]\n" +
            "|====================\n" +
            "|Метрика|Значение\n" +
            "|Ресурсы|'/nginx_logs' \n" +
            "|Начальная дата|-\n" +
            "|Конечная дата|-\n" +
            "|Количество запросов|51462\n" +
            "|Средний размер запроса|659509b\n" +
            "|====================\n" +
            "==== Запрашиваемые ресурсы\n" +
            "[width=\"100%\",options=\"header\", cols=\"^,^\"]\n" +
            "|====================\n" +
            "|Ресурс|Количество\n" +
            "|/downloads/product_3|73\n" +
            "|/downloads/product_1|30285\n" +
            "|/downloads/product_2|21104\n" +
            "|====================\n" +
            "==== Коды ответа\n" +
            "\n" +
            "[width=\"100%\",options=\"header\", cols=\"<,^,>\"]\n" +
            "|====================\n" +
            "|Код|Имя|Количество\n" +
            "|304|Not Modified|13330\n" +
            "|416|Range Not Satisfiable|4\n" +
            "|403|Forbidden|38\n" +
            "|404|Not Found|33876\n" +
            "|200|OK|4028\n" +
            "|206|Partial Content|186\n" +
            "|====================\n" +
            "==== Топ 10 адресов\n" +
            "\n" +
            "[width=\"100%\",options=\"header\", cols=\"<,^\"]\n" +
            "|====================\n" +
            "|Адрес|Количество запросов\n" +
            "|216.46.173.126|2350\n" +
            "|180.179.174.219|1720\n" +
            "|204.77.168.241|1439\n" +
            "|65.39.197.164|1365\n" +
            "|80.91.33.133|1202\n" +
            "|84.208.15.12|1120\n" +
            "|74.125.60.158|1084\n" +
            "|119.252.76.162|1064\n" +
            "|79.136.114.202|628\n" +
            "|54.207.57.55|532\n" +
            "|====================\n" +
            "==== Топ 10 Дат\n" +
            "\n" +
            "[width=\"100%\",options=\"header\", cols=\"<,^\"]\n" +
            "|====================\n" +
            "|Дата|Количество логов\n" +
            "|23/MAY/2015|2892\n" +
            "|27/MAY/2015|2887\n" +
            "|21/MAY/2015|2881\n" +
            "|26/MAY/2015|2879\n" +
            "|22/MAY/2015|2879\n" +
            "|30/MAY/2015|2876\n" +
            "|2/JUNE/2015|2864\n" +
            "|31/MAY/2015|2863\n" +
            "|18/MAY/2015|2855\n" +
            "|24/MAY/2015|2853\n" +
            "|====================\n";
        String response = "";
        try {
            try (BufferedReader bf1 = Files.newBufferedReader(firstFile)) {

                String line;
                while ((line = bf1.readLine()) != null) {
                    response += line + '\n';
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(required, response);
        Files.deleteIfExists(Path.of("pretty-logs.adoc"));
    }
}
