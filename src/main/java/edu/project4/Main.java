package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:HideUtilityClassConstructor"})
public class Main {
    //Для изображения размером 1920х1080 можно
    //взять XMIN=-1.777,XMAX=1.777,YMIN=-1,YMAX=1
    //В этом случае в большинстве нелинейных преобразований с боков не будет оставаться черных областей
    private static final double XMIN = -1.;
    private static final double XMAX = 1.;
    private static final double YMIN = -1;
    private static final double YMAX = 1;
    private static Pixel[][] pixels;
    private static int height = 1080;
    private static int width = 1920;

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        try {
            BufferedImage image = new BufferedImage(width, height, 1);
            render(10000, 50, 10000, height, width);
            correction(height, width);
            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[0].length; j++) {
                    Pixel tempPixel = pixels[i][j];
                    Color newColor = new Color(tempPixel.getRed(), tempPixel.getGreen(), tempPixel.getBlue());
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File("qwe.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void render(int n, int eqCount, int it, int xRes, int yRes) {
        //Генерируем eqCount аффинных преобразований со стартовыми цветами;
        Transformation[] transformations = createTransformations(eqCount);
        Random random = new Random();
        for (int num = 0; num < n; num++) {

            double newX = random.nextDouble(XMIN, XMAX);
            double newY = random.nextDouble(YMIN, YMAX);
            //Первые 20 итераций точку не рисуем, т.к. сначала надо найти начальную
            for (int step = -20; step < it; step++) {
                //Выбираем одно из аффинных преобразований
                //и применяем его
                var afine = transformations[random.nextInt(0, eqCount)];
                newX = afine.a() * newX + afine.b() * newY + afine.c();
                newY = afine.d() * newX + afine.e() * newY + afine.f();
                //Применяем нелинейное преобразование;
                Point nonLinear = getTransform(newX, newY, NonLinearTransforms.SIN);
                newX = nonLinear.x();
                newY = nonLinear.y();
                if (step >= 0 && XMIN <= newX && newX <= XMAX && YMIN <= newY && newY <= YMAX) {
                    //Вычисляем координаты точки, а затем задаем цвет
                    int x1 = (int) (xRes - Math.floor(((XMAX - newX) / (XMAX - XMIN)) * xRes));
                    int y1 = (int) (yRes - Math.floor(((YMAX - newY) / (YMAX - YMIN)) * yRes));
                    //Если точка попала в область изображения
                    if (x1 < xRes && y1 < yRes) {
                        //то проверяем, первый ли раз попали в нее
                        if (pixels[x1][y1].getCounter() == 0) {
                            //Попали в первый раз, берем стартовый цвет у соответствующего аффинного преобразования
                            pixels[x1][y1].setRed(afine.red());
                            pixels[x1][y1].setGreen(afine.green());
                            pixels[x1][y1].setBlue(afine.blue());
                        } else {
                            //Попали не в первый раз, считаем так:
                            pixels[x1][y1].setRed((pixels[x1][y1].getRed() + afine.red()) / 2);
                            pixels[x1][y1].setGreen((pixels[x1][y1].getGreen() + afine.green()) / 2);
                            pixels[x1][y1].setBlue((pixels[x1][y1].getBlue() + afine.blue()) / 2);
                        }
                        //Увеличиваем счетчик точки на единицу
                        pixels[x1][y1].incrementCounter();
                    }
                }
            }
        }
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static Transformation[] createTransformations(int count) {
        int cnt = 0;
        Transformation[] transformations = new Transformation[count];
        while (cnt < count) {
            Random random = new Random();
            double a = random.nextDouble(-1, 1);
            double d = random.nextDouble(-1, Math.sqrt(1 - a * a));
            double b = random.nextDouble(-1, 1);
            double e = random.nextDouble(-1, Math.sqrt(1 - b * b));
            if (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d)) {
                double c = random.nextDouble(-1.5, 1.5);
                double f = random.nextDouble(-1.5, 1.5);
                int red = random.nextInt(0, 255);
                int green = random.nextInt(0, 255);
                int blue = random.nextInt(0, 255);
                transformations[cnt] = new Transformation(a, b, c, d, e, f, red, green, blue);
                cnt++;
            }
        }
        return transformations;
    }

    public static void correction(int xRes, int yRes) {
        int max = 0;
        double gammaIncrease = 0;
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (pixels[row][col].getCounter() != 0) {
                    if (pixels[row][col].getCounter() > max) {
                        max = pixels[row][col].getCounter();
                    }
                }
            }
        }
        double maxLog = Math.log10(max);
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                double gammaFactor = Math.log10(pixels[row][col].getCounter()) / maxLog;
                gammaFactor += gammaFactor * gammaIncrease;
                pixels[row][col]
                    .setRed(Math.min((int) (pixels[row][col].getRed() * gammaFactor), 255));
                pixels[row][col]
                    .setGreen(Math.min((int) (pixels[row][col].getGreen() * gammaFactor), 255));
                pixels[row][col]
                    .setBlue(Math.min((int) (pixels[row][col].getBlue() * gammaFactor), 255));
            }
        }
    }

    public static Point getTransform(double x, double y, NonLinearTransforms nonLinearTransform) {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        double rsth = Math.pow(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), Math.sin(theta));
        Point transformed = switch (nonLinearTransform) {
            case SIN -> new Point(Math.sin(x), Math.sin(y));
            case SPHERIC -> new Point(x / (r * r), y / (r * r));
            case POLAR -> new Point(Math.atan2(y, x) / Math.PI, r - 1);
            case HEART -> new Point(
                -r * Math.cos(r * theta),
                r * Math.sin(r * theta)
            );
            case DISK -> new Point(
                1 / Math.PI * theta * Math.sin(Math.PI * r),
                1 / Math.PI * theta * Math.cos(Math.PI * r)
            );
            case DIAMOND -> new Point(Math.sin(theta) * Math.cos(r), Math.cos(theta) * Math.sin(r));
            case POWER -> new Point(rsth * Math.cos(theta), rsth * Math.sin(theta));
        };
        return transformed;
    }
}

