package edu.hw3.Task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class Task6 implements StockMarket {
    public Queue<Stock> queue = new PriorityQueue<>();

    public void add(Stock stock) {
        queue.add(stock);
    }

    public void remove(Stock stock) {
        if (!queue.contains(stock)) {
            throw new IllegalArgumentException("В бирже нет данной акции");
        }
        queue.remove(stock);
    }

    public Stock mostValuableStock() {
        return queue.peek();
    }
}
