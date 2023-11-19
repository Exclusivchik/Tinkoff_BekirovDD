package edu.project2;

public class Cell {
    private boolean wallRight;
    private boolean wallBottom;
    private int set;
    private String color = " ";

    public Cell(boolean wallRight, boolean wallBottom, int set, String color) {
        this.set = set;
        this.wallBottom = wallBottom;
        this.wallRight = wallRight;
        this.color = color;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public void setWallRight(boolean wallRight) {
        this.wallRight = wallRight;
    }

    public void setWallBottom(boolean wallBottom) {
        this.wallBottom = wallBottom;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getWallBottom() {
        return wallBottom;
    }

    public int getSet() {
        return set;
    }

    public boolean getWallRight() {
        return wallRight;
    }

    public String getColor() {
        return color;
    }

    public Cell getCopy() {
        return new Cell(wallRight, wallBottom, set, color);
    }
}
