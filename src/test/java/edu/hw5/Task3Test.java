package edu.hw5;

import edu.hw5.Task3.DayMonthYear2WithSlashHandler;
import edu.hw5.Task3.DayMonthYear4WithSlashHandler;
import edu.hw5.Task3.DaysAgoHandler;
import edu.hw5.Task3.Handler;
import edu.hw5.Task3.TodayHandler;
import edu.hw5.Task3.TomorrowHandler;
import edu.hw5.Task3.YearMonth1Date1Handler;
import edu.hw5.Task3.YearMonth2Date2Handler;
import edu.hw5.Task3.YesterdayHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

public class Task3Test {
    Handler yearMonth2Date2 = new YearMonth2Date2Handler();
    Handler yearMonth1Date1 = new YearMonth1Date1Handler();
    Handler dayMonthYear4WithSlash = new DayMonthYear4WithSlashHandler();
    Handler dayMonthYear2WithSlash = new DayMonthYear2WithSlashHandler();
    Handler tomorrow = new TomorrowHandler();
    Handler today = new TodayHandler();
    Handler yesterday = new YesterdayHandler();
    Handler daysAgo = new DaysAgoHandler();

    private void setNextHandlers() {
        yearMonth2Date2.setNextHandler(yearMonth1Date1);
        yearMonth1Date1.setNextHandler(dayMonthYear4WithSlash);
        dayMonthYear4WithSlash.setNextHandler(dayMonthYear2WithSlash);
        dayMonthYear2WithSlash.setNextHandler(tomorrow);
        tomorrow.setNextHandler(today);
        today.setNextHandler(yesterday);
        yesterday.setNextHandler(daysAgo);
        yesterday.setNextHandler(daysAgo);
    }

    @Test
    @DisplayName("test1")
    void test1() {
        setNextHandlers();
        String request = "2020-10-10";

        Optional<LocalDate> required = Optional.of(LocalDate.of(2020, 10, 10));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test2")
    void test2() {
        setNextHandlers();
        String request = "2020-12-2";

        Optional<LocalDate> required = Optional.of(LocalDate.of(2020, 12, 2));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test3")
    void test3() {
        setNextHandlers();
        String request = "1/3/1976";

        Optional<LocalDate> required = Optional.of(LocalDate.of(1976, 3, 1));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test4")
    void test4() {
        setNextHandlers();
        String request = "1/3/20";

        Optional<LocalDate> required = Optional.of(LocalDate.of(2020, 3, 1));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test5")
    void test5() {
        setNextHandlers();
        String request = "tomorrow";

        Optional<LocalDate> required = Optional.of(LocalDate.now().plusDays(1));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test6")
    void test6() {
        setNextHandlers();
        String request = "tomorrow";

        Optional<LocalDate> required = Optional.of(LocalDate.now().plusDays(1));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test7")
    void test7() {
        setNextHandlers();
        String request = "today";

        Optional<LocalDate> required = Optional.of(LocalDate.now());

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test8")
    void test8() {
        setNextHandlers();
        String request = "yesterday";

        Optional<LocalDate> required = Optional.of(LocalDate.now().minusDays(1));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test9")
    void test9() {
        setNextHandlers();
        String request = "1 day ago";

        Optional<LocalDate> required = Optional.of(LocalDate.now().minusDays(1));

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test10")
    void test10() {
        setNextHandlers();
        String request = "20 days ago";

        Optional<LocalDate> required = Optional.of(LocalDate.now().minusDays(20));
        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), required);
    }

    @Test
    @DisplayName("test11")
    void test11() {
        setNextHandlers();
        String request = "Igor Goffman";

        Assertions.assertEquals(yearMonth1Date1.handleRequest(request), Optional.empty());
    }
}
