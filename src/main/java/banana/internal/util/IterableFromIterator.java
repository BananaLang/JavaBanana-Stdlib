package banana.internal.util;

import java.util.Iterator;

public final class IterableFromIterator<E> implements Iterable<E> {
    private final Iterator<E> iterator;

    private IterableFromIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    public static <E> IterableFromIterator<E> create(Iterator<E> iterator) {
        return new IterableFromIterator<>(iterator);
    }

    @Override
    public Iterator<E> iterator() {
        return iterator;
    }
}
