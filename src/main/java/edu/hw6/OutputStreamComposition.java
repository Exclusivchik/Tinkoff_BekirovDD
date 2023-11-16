package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public final class OutputStreamComposition {
    private OutputStreamComposition() {
    }

    public static void write(String text) {

        try (OutputStream fileOutputStream = Files.newOutputStream(Path.of("output.txt"), StandardOpenOption.CREATE);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println(text);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
