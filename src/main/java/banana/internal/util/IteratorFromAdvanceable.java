package banana.internal.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class IteratorFromAdvanceable<E> implements Iterator<E> {
    private final AdvanceableIterator<E> iterator;
    private boolean valueReady;
    private E next;

    private IteratorFromAdvanceable(AdvanceableIterator<E> iterator) {
        this.iterator = iterator;
    }

    public static <E> IteratorFromAdvanceable<E> create(AdvanceableIterator<E> iterator) {
        return new IteratorFromAdvanceable<>(iterator);
    }

    @Override
    public boolean hasNext() {
        if (!valueReady) {
            try {
                next = iterator.advance();
                valueReady = true;
            } catch (NoSuchElementException e) {
                valueReady = false;
            }
        }
        return valueReady;
    }

    @Override
    public E next() {
        if (!valueReady) {
            next = iterator.advance();
        }
        E value = next;
        valueReady = false;
        next = null;
        return value;
    }
}
