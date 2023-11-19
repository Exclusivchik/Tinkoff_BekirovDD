package edu.project2;

public record Coordinates(int row, int col) {
    public Coordinates getCopy() {
        return new Coordinates(row, col);
    }
}
