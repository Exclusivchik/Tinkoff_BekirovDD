package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> elems;
    private int cursor;

    public BackwardIterator(Collection<T> collection) {
        elems = List.copyOf(collection);
        cursor = this.elems.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return this.cursor > -1;
    }

    @Override
    public T next() {
        return elems.get(cursor--);
    }
}
