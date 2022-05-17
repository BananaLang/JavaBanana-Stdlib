package banana.internal.util;

/**
 * All public methods in this class start with a $ to prevent being called from BananaLang. The methods in this class
 * are highly specialized, and are not likely to be useful outside of compiler generated code.
 */
public class InternalUtil {
    /**
     * Throws an NPE with source code information to help pinpoint the error. This method returns a
     * NullPointerException so that the compiler generated code can use an athrow after calling this method to indicate
     * to the JIT and other bytecode analysis code that calling this method is a terminal operation.
     */
    public static NullPointerException $nullAssertionFailure(String src) throws NullPointerException {
        throw new NullPointerException("Non-null assertion failed because " + src + " evaluated to null");
    }

    public static <E> Iterable<E> $createGenerator(AdvanceableIterator<E> gen) {
        return IterableFromIterator.create(IteratorFromAdvanceable.create(gen));
    }
}
