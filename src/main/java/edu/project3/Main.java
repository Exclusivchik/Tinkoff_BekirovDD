package edu.project3;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    private Main() {
    }

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
                format = args[iter++];
                last = 0;
            }
            if ("--from".equals(args[iter])) {
                iter++;
                from = args[iter++];
                last = 0;
            }
            if ("--to".equals(args[iter])) {
                iter++;
                to = args[iter++];
                last = 0;
            }
            if ("--path".equals(args[iter])) {
                last = 1;
                iter++;
            }
            if (last == 1) {
                paths.add(args[iter]);
                iter++;
            }
        }
    }
}
