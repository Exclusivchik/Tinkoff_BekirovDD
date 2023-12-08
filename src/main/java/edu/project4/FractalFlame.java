package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class FractalFlame {
    //Для изображения размером 1920х1080 можно
    //взять X_MIN=-1.777,X_MAX=1.777,Y_MIN=-1,Y_MAX=1
    //В этом случае в большинстве нелинейных преобразований с боков не будет оставаться черных областей
    private static final double X_MIN = -1.;
    private static final double X_MAX = 1.;
    private static final double Y_MIN = -1.;
    private static final double Y_MAX = 1.;
    private static final double C_F_BOUND = 1.5;
    private static final int MAX_COLOR_VALUE = 255;
    private static final int START_VALUE_FOR_ITERATIONS = -20;
    private static Pixel[][] pixels;
    private final int height;
    private final int width;

    public FractalFlame(int height, int width) {
        this.height = height;
        this.width = width;
        pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new Pixel();
            }
        }
    }

    public void createFile(String fileName, ImageType imageType) {
        if (imageType == null) {
            throw new IllegalArgumentException();
        }
        try {
            BufferedImage image = new BufferedImage(width, height, 1);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Pixel tempPixel = pixels[i][j];
                    Color newColor = new Color(tempPixel.getRed(), tempPixel.getGreen(), tempPixel.getBlue());
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            String extension = switch (imageType) {
                case PNG -> "png";
                case BMP -> "bmp";
                case JPEG -> "jpeg";
                case JPG -> "jpg";
            };
            File output = new File(fileName + "." + extension);
            ImageIO.write(image, extension, output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void render(int n, int eqCount, int it, int symmetry, NonLinearTransforms transform, int threads) {
        if (transform == null) {
            throw new IllegalArgumentException();
        }
        //Генерируем eqCount аффинных преобразований со стартовыми цветами;
        Transformation[] transformations = createTransformations(eqCount);
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < n; i++) {
                executorService.submit(() -> {
                    double newX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
                    double newY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);
                    iterate(it, newX, newY, symmetry, transformations, transform);
                });
            }
        }
    }

    private void iterate(
        int iterations, double x, double y,
        int symmetry, Transformation[] transformations, NonLinearTransforms transform
    ) {
        double newX = x;
        double newY = y;
        //Первые 20 итераций точку не рисуем, т.к. сначала надо найти начальную
        for (int step = START_VALUE_FOR_ITERATIONS; step < iterations; step++) {
            //Выбираем одно из аффинных преобразований
            //и применяем его
            var afine = transformations[ThreadLocalRandom.current().nextInt(0, transformations.length)];
            newX = afine.a() * newX + afine.b() * newY + afine.c();
            newY = afine.d() * newX + afine.e() * newY + afine.f();
            //Применяем нелинейное преобразование;
            Point nonLinear = getTransform(newX, newY, transform);
            newX = nonLinear.x();
            newY = nonLinear.y();
            var theta2 = 0.0;
            for (int s = 0; s < symmetry; s++) {
                theta2 += (Math.PI * 2) / symmetry;
                var rotated = rotate(new Point(newX, newY), theta2);
                newX = rotated.x();
                newY = rotated.y();
                if (step >= 0 && X_MIN <= newX && newX <= X_MAX && Y_MIN <= newY && newY <= Y_MAX) {
                    //Вычисляем координаты точки, а затем задаем цвет
                    int x1 = (int) (height - Math.floor(((X_MAX - newX) / (X_MAX - X_MIN)) * height));
                    int y1 = (int) (width - Math.floor(((Y_MAX - newY) / (Y_MAX - Y_MIN)) * width));
                    //Если точка попала в область изображения
                    if (x1 < height && y1 < width) {
                        synchronized (pixels[x1][y1]) {
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
    }

    public static Transformation[] createTransformations(int count) {
        int cnt = 0;
        Transformation[] transformations = new Transformation[count];
        while (cnt < count) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            double a = random.nextDouble(-1, 1);
            double d = random.nextDouble(-1, Math.sqrt(1 - a * a));
            double b = random.nextDouble(-1, 1);
            double e = random.nextDouble(-1, Math.sqrt(1 - b * b));
            if (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d)) {
                double c = random.nextDouble(-C_F_BOUND, C_F_BOUND);
                double f = random.nextDouble(-C_F_BOUND, C_F_BOUND);
                int red = random.nextInt(0, MAX_COLOR_VALUE);
                int green = random.nextInt(0, MAX_COLOR_VALUE);
                int blue = random.nextInt(0, MAX_COLOR_VALUE);
                transformations[cnt] = new Transformation(a, b, c, d, e, f, red, green, blue);
                cnt++;
            }
        }
        return transformations;
    }

    public static Point getTransform(double x, double y, NonLinearTransforms nonLinearTransform) {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        double rsth = Math.pow(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), Math.sin(theta));
        return switch (nonLinearTransform) {
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
    }

    public void correction(double gammaIncrease) {
        int max = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (pixels[row][col].getCounter() != 0) {
                    max = Math.max(max, pixels[row][col].getCounter());
                }
            }
        }
        double maxLog = Math.log10(max);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double gammaFactor = Math.log10(pixels[row][col].getCounter()) / maxLog;
                gammaFactor += gammaFactor * gammaIncrease;
                pixels[row][col]
                    .setRed(Math.min((int) (pixels[row][col].getRed() * gammaFactor), MAX_COLOR_VALUE));
                pixels[row][col]
                    .setGreen(Math.min((int) (pixels[row][col].getGreen() * gammaFactor), MAX_COLOR_VALUE));
                pixels[row][col]
                    .setBlue(Math.min((int) (pixels[row][col].getBlue() * gammaFactor), MAX_COLOR_VALUE));
            }
        }
    }

    private Point rotate(Point point, double theta) {
        double xRotated = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double yRotated = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(xRotated, yRotated);
    }
}
