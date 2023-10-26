package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Тест1")
    void test1() {
        //given
        String[] info = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        //when
        var sortedContacts = Task5.parseContacts(info, "ASC");
        //then
        List<Contact> total = new ArrayList<>();
        total.add(Task5.createContact("Thomas Aquinas"));
        total.add(Task5.createContact("Rene Descartes"));
        total.add(Task5.createContact("David Hume"));
        total.add(Task5.createContact("John Locke"));
        assertThat(sortedContacts).isEqualTo(total);
    }

    @Test
    @DisplayName("Тест2")
    void test2() {
        //given
        String[] info = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        //when
        var sortedContacts = Task5.parseContacts(info, "DESC");
        //then
        List<Contact> total = new ArrayList<>();
        total.add(Task5.createContact("Carl Gauss"));
        total.add(Task5.createContact("Leonhard Euler"));
        total.add(Task5.createContact("Paul Erdos"));
        assertThat(sortedContacts).isEqualTo(total);
    }

    @Test
    @DisplayName("Тест3")
    void test3() {
        //given
        String[] info = {};
        //when
        var sortedContacts = Task5.parseContacts(info, "DESC");
        //then
        List<Contact> total = new ArrayList<>();
        assertThat(sortedContacts).isEqualTo(total);
    }

    @Test
    @DisplayName("Тест4")
    void test4() {
        //given
        String[] info = null;
        //when

        //then
        Assertions.assertThrows(NullPointerException.class, () -> Task5.parseContacts(info, "DESC"));
    }

    @Test
    @DisplayName("Тест5")
    void test5() {
        //given
        String[] info = {"asdasd asdas", "dsadqweq asdasd", "Igor Goffman"};
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task5.parseContacts(info, "78cloth"));
    }

    @Test
    @DisplayName("Тест6")
    void test6() {
        //given
        String[] info = {"Alpha", "Emma Johnson",
            "Jessica Brown", "Adam Johnson",
            "Daniel", "John Smith", "Oliver Smith"};
        //when
        List<Contact> total = new ArrayList<>();
        total.add(Task5.createContact("Alpha"));
        total.add(Task5.createContact("Jessica Brown"));
        total.add(Task5.createContact("Daniel"));
        total.add(Task5.createContact("Adam Johnson"));
        total.add(Task5.createContact("Emma Johnson"));
        total.add(Task5.createContact("John Smith"));
        total.add(Task5.createContact("Oliver Smith"));
        //then
        var sortedContacts = Task5.parseContacts(info, "ASC");
        assertThat(sortedContacts).isEqualTo(total);
    }
}
