package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {

    @Test
    @DisplayName("Успешное выполнение")
    public void test1() {
        //given
        ConnectionManager manager = new DefaultConnectionManager();
        int maxAttempts = 3;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        Assertions.assertDoesNotThrow(() -> executor.updatePackages());
    }

    @Test
    @DisplayName("Проваленное выполнение")
    public void test2() {
        //given
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 3;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        Assertions.assertThrows(ConnectionException.class, () -> executor.updatePackages());
    }

    @Test
    @DisplayName("Успешное выполнение своей команды")
    public void test3() {
        //given
        ConnectionManager manager = new DefaultConnectionManager();
        int maxAttempts = 5;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        Assertions.assertDoesNotThrow(() -> executor.tryExecute("Igor Goffman"));
    }

    @Test
    @DisplayName("Превышено максимальное кол-во попыток")
    public void test4() {
        //given
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 2;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        ConnectionException exception = Assertions.assertThrows(ConnectionException.class, () -> executor.tryExecute("78 Spartak"));

        //then
        Assertions.assertEquals("Failed to execute command after 2 attempts", exception.getMessage());
        Assertions.assertNotNull(exception.getCause());
    }
}
