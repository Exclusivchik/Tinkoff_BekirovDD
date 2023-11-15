package edu.hw2.Task2;

public class Square extends Rectangle {

    public Square(int width) {
        super(width, width);
    }

    @Override
    public Square setWidth(int width) {
        return new Square(width);
    }

    @Override
    public Square setHeight(int height) {
        return new Square(height);
    }
}
