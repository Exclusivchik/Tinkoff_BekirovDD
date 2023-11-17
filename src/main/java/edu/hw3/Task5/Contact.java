package edu.hw3.Task5;

public record Contact(String surname, String name) implements Comparable<Contact> {
    @Override
    public int compareTo(Contact o) {
        int paramForSurname = this.surname.compareTo(o.surname);
        if (paramForSurname == 0) {
            return this.name.compareTo(o.name);
        }
        return paramForSurname;
    }
}
