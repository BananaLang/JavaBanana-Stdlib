package banana.internal.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class GeneratorIterator<E> implements Iterator<E> {
    private final GeneratorFunction<E> fn;
    private final int[] state = {0};
    private boolean valueReady;
    private E next;

    public GeneratorIterator(GeneratorFunction<E> fn) {
        this.fn = fn;
    }

    @Override
    public boolean hasNext() {
        if (!valueReady) {
            try {
                next = fn.advance(state);
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
            next = fn.advance(state);
        }
        E value = next;
        valueReady = false;
        next = null;
        return value;
    }
}
