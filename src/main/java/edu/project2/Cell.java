package edu.project2;

public class Cell {
    private boolean wallRight = false;
    private boolean wallBottom = false;
    private int set = -1;

    public void setSet(int set) {
        this.set = set;
    }

    public void setWallRight(boolean wallRight) {
        this.wallRight = wallRight;
    }

    public void setWallBottom(boolean wallBottom) {
        this.wallBottom = wallBottom;
    }

    public boolean getWallBottom() {
        return wallBottom;
    }

    public boolean getWallRight() {
        return wallRight;
    }

    public int getSet() {
        return set;
    }

    public Cell getCopy() {
        Cell newCell = new Cell();
        newCell.setSet(set);
        newCell.setWallRight(wallRight);
        newCell.setWallBottom(wallBottom);
        return newCell;
    }
}
