package edu.project4;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:HideUtilityClassConstructor"})
public class NewMain {
     public static void main(String[] args) {
        int height = 2000;
        int width = 2000;
        FractalFlame fractalFlame = new FractalFlame(height, width);
        fractalFlame.render(10000, 20, 10000, NonLinearTransforms.DISK, 5);
        fractalFlame.correction(0);
        fractalFlame.createFile("newFractal", ImageType.JPG);
    }
}
