package edu.project3;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"checkstyle:LineLength", "checkstyle:MagicNumber"})
public class NginxLog {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(.*) - (.*) \\[(.*)] \"(.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"$");

    private final String remoteAddr;
    private final String remoteUser;
    private final OffsetDateTime timeLocal;
    private final String request;
    private final int status;
    private final int bodyBytesSent;
    private final String httpReferer;
    private final String httpUserAgent;

    public static OffsetDateTime parseDateTimeString(String dateTimeString) {
        String stringOffset = dateTimeString.split(" ")[1];
        stringOffset = stringOffset.substring(0, 3) + ":" + stringOffset.substring(3);
        ZoneOffset offset = ZoneOffset.of(stringOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString.split(" ")[0], formatter);
        return OffsetDateTime.of(localDateTime, offset);
    }

    public NginxLog(String logLine, String origin) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.find()) {
            remoteAddr = matcher.group(1);
            remoteUser = matcher.group(2);
            timeLocal = parseDateTimeString(matcher.group(3));
            request = matcher.group(4);
            status = Integer.parseInt(matcher.group(5));
            bodyBytesSent = Integer.parseInt(matcher.group(6));
            httpReferer = matcher.group(7);
            httpUserAgent = matcher.group(8);
        } else {
            throw new IllegalArgumentException("Invalid log format: " + logLine);
        }
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public OffsetDateTime getTimeLocal() {
        return timeLocal;
    }

    public String getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public int getBodyBytesSent() {
        return bodyBytesSent;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }
}
