package edu.project4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FractalFlameTest {
    @Test
    @DisplayName("Бизнес логика")
    void test1() throws IOException {
        int height = 1080;
        int width = 1920;
        FractalFlame fractalFlame = new FractalFlame(height, width);
        fractalFlame.render(1000, 1000, 1000, 0, NonLinearTransforms.DISK, 5);
        fractalFlame.correction(0.2);
        fractalFlame.createFile("testFractal", ImageType.JPG);
        File file = new File("testFractal.jpg");
        Assertions.assertTrue(file.exists());
        BufferedImage bimg = ImageIO.read(new File("testFractal.jpg"));
        int parsedWidth = bimg.getWidth();
        int parsedHeight = bimg.getHeight();
        Assertions.assertEquals(height, parsedHeight);
        Assertions.assertEquals(width, parsedWidth);
    }

    @Test
    @DisplayName("Проверка входных данных")
    void test2() {
        int height = 1080;
        int width = 1920;
        FractalFlame fractalFlame = new FractalFlame(height, width);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fractalFlame.render(1000, 1000, 1000, 0, null, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> fractalFlame.createFile("testFractal", null));
    }
}
