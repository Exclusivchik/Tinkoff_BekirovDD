package edu.project3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"checkstyle:LineLength", "checkstyle:MagicNumber"})
public class NginxLogParser {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - (.*) \\[(.*)] \"(.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"$");

    private final String ipAddress;
    private final String remoteUser;
    private final String timeLocal;
    private final String request;
    private final int status;
    private final int bodyBytesSent;
    private final String httpReferer;
    private final String httpUserAgent;

    public NginxLogParser(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.find()) {
            ipAddress = matcher.group(1);
            remoteUser = matcher.group(2);
            timeLocal = matcher.group(3);
            request = matcher.group(4);
            status = Integer.parseInt(matcher.group(5));
            bodyBytesSent = Integer.parseInt(matcher.group(6));
            httpReferer = matcher.group(7);
            httpUserAgent = matcher.group(8);
        } else {
            throw new IllegalArgumentException("Invalid log format: " + logLine);
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public String getTimeLocal() {
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
