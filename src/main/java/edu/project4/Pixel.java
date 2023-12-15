package edu.project4;

public class Pixel {
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int counter = 0;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
