package banana.internal.util;

import banana.builtin.FormatString;

/**
 * All public methods in this class start with a $ to prevent being called from BananaLang. The methods in this class
 * are highly specialized, and are not likely to be useful outside of compiler generated code.
 */
public class InternalUtil {
    public static final Object[] $EMPTY_OBJECT_ARRAY = new Object[0];
    static final Object GENERATOR_COMPLETE = new Object();

    private InternalUtil() {
    }

    /**
     * Throws an NPE with source code information to help pinpoint the error. This method returns a
     * NullPointerException so that the compiler generated code can use an athrow after calling this method to indicate
     * to the JIT and other bytecode analysis code that calling this method is a terminal operation.
     */
    public static NullPointerException $nullAssertionFailure(String src) throws NullPointerException {
        throw new NullPointerException("Non-null assertion failed because " + src + " evaluated to null");
    }

    public static <E> Iterable<E> $createGenerator(GeneratorFunction<E> gen) {
        return new IterableFromIterator<>(new GeneratorIterator<>(gen));
    }

    public static Object $generatorComplete() {
        return GENERATOR_COMPLETE;
    }

    public static FormatString $untrustedFormatString(String[] stringValues, Object[] objectValues) {
        if (stringValues.length != objectValues.length + 1) {
            throw new IllegalArgumentException("Format string argument length mismatch");
        }
        return new FormatStringImpl(stringValues.clone(), objectValues != null ? objectValues.clone() : $EMPTY_OBJECT_ARRAY);
    }

    public static FormatString $trustedFormatString(String[] stringValues, Object[] objectValues) {
        return new FormatStringImpl(stringValues, objectValues);
    }
}
