package edu.hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final int OK_CODE = 200;

    public long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == OK_CODE) {
                String responseBody = response.body();
                String[] idsStringArray = responseBody.substring(1, responseBody.length() - 1).split(",");
                long[] ids = new long[idsStringArray.length];
                for (int i = 0; i < idsStringArray.length; i++) {
                    ids[i] = Long.parseLong(idsStringArray[i]);
                }
                return ids;
            }
        } catch (Exception e) {
        }
        return new long[0];
    }

    public String news(long id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == OK_CODE) {
                String responseBody = response.body();
                if ("null".equals(responseBody)) {
                    throw new RuntimeException();
                }
                Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
                Matcher matcher = pattern.matcher(responseBody);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return "";
    }
}

