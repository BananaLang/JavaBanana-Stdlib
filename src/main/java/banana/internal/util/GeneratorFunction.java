package banana.internal.util;

public interface GeneratorFunction<E> {
    E advance(int[] state);
}
