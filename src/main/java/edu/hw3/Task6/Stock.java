package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public record Stock(int value) implements Comparable<Stock> {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Stock otherStock = (Stock) obj;
        return this.value == otherStock.value();
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(this.value).hashCode();
    }

    @Override
    public int compareTo(@NotNull Stock stock) {
        return this.value < stock.value() ? 1 : -1;
    }
}
