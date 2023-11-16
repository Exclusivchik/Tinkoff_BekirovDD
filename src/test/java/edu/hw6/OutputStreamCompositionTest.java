package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OutputStreamCompositionTest {
    @Test
    void test1() {
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        OutputStreamComposition.write(text);

        File file = new File("output.txt");

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            String s = bufferedReader.readLine();
            Assertions.assertEquals(s, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
