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
            next = fn.advance(state);
            valueReady = next != InternalUtil.GENERATOR_COMPLETE;
        }
        return valueReady;
    }

    @Override
    public E next() {
        if (!valueReady) {
            if (next == InternalUtil.GENERATOR_COMPLETE) {
                throw new NoSuchElementException();
            }
            next = fn.advance(state);
        }
        E value = next;
        valueReady = false;
        next = null;
        return value;
    }
}
