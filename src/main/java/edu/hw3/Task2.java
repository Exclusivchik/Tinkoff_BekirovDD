package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String sequence) {
        List<String> clusters = new ArrayList<>();
        int balance = 0;
        String tempCluster = "";
        for (int i = 0; i < sequence.length(); i++) {
            char bracket = sequence.charAt(i);

            tempCluster = tempCluster + bracket;
            if (bracket == '(') {
                balance++;
            } else if (bracket == ')') {
                balance--;
            } else {
                return new ArrayList<>();
            }
            if (balance < 0) {
                return new ArrayList<>();
            }
            if (balance == 0) {
                clusters.add(tempCluster);
                tempCluster = "";
            }
        }
        return clusters;
    }
}
