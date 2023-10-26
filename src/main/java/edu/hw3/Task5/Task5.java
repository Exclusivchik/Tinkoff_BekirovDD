package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Task5 {
    private Task5() {
    }

    public static Contact createContact(String person) {
        String[] splited = person.split(" ");
        if (splited.length == 1) {
            return new Contact(splited[0], "");
        } else {
            return new Contact(splited[1], splited[0]);
        }
    }

    public static List<Contact> parseContacts(String[] info, String order) {
        List<Contact> sortedContacts = new ArrayList<>();
        for (String person: info) {
            sortedContacts.add(createContact(person));
        }
        if (order.equals("ASC")) {
            Collections.sort(sortedContacts);
        } else if (order.equals("DESC")) {
            sortedContacts.sort(Collections.reverseOrder());
        } else {
            throw new IllegalArgumentException("Wrong format of order");
        }
        return sortedContacts;
    }

}
