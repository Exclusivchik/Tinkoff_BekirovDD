package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileClonerTest {
    @Test
    @DisplayName("проверка создания копий")
    void test1() {
        for (int i = 0; i < 3; i++) {
            Path newFilePath = FileCloner.cloneFile(Path.of("IgorGoffman").toString());
            Assertions.assertTrue(Files.exists(newFilePath));
            try {
                Files.delete(newFilePath);
            } catch (IOException e) {
            }

        }
    }

    @Test
    @DisplayName("проверка выброса исключений")
    void test2() {
        Assertions.assertThrows(RuntimeException.class, () -> FileCloner.cloneFile(Path.of("Spartak78").toString()));
    }
}
