package edu.hw2.Task2;

public class Rectangle {
    private final int width;
    private final int height;

    public Rectangle() {
        this.width  = 0;
        this.height = 0;
    }

    public Rectangle(int height, int width) {
        this.width  = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(this.height, width);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(height, this.width);
    }

    public double area() {
        return width * height;
    }
}
