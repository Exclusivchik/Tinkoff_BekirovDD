package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static edu.hw6.AbstractFilter.globMatches;
import static edu.hw6.AbstractFilter.magicNumber;
import static edu.hw6.AbstractFilter.regexContains;

public class AbstractFilterTest {
    @Test
    void test1() {

        DirectoryStream.Filter<Path> filter = AbstractFilter
            .regularFile()
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(globMatches(".png"))
            .and(regexContains(".*a.*"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(System.getProperty("user.dir")), filter)) {
            ArrayList<Path> response = new ArrayList<>();
            for (Path path : entries) {
                response.add(path.getFileName());
            }
            var required = List.of(Path.of("goffman.png"), Path.of("spartak.png"));
            Assertions.assertEquals(required, response);
        } catch (IOException e) {
        }
    }
}
