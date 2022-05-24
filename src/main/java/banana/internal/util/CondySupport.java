package banana.internal.util;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;

import banana.builtin.FormatString;
import banana.builtin.Int;

public class CondySupport {
    public static Int intConstant(MethodHandles.Lookup lookup, String name, Class<Int> type, int value) {
        if (!Int.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }
        return Int.valueOf(value);
    }

    public static Int intConstant(MethodHandles.Lookup lookup, String name, Class<Int> type, long value) {
        if (!Int.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }
        return Int.valueOf(value);
    }

    public static Int intConstant(MethodHandles.Lookup lookup, String name, Class<Int> type, String value) {
        if (!Int.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }
        return Int.valueOf(new BigInteger(value));
    }

    public static FormatString formatStringConstant(
        MethodHandles.Lookup lookup, String name, Class<? extends FormatString> type,
        String[] stringValues, Object[] objectValues
    ) {
        if (!FormatString.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException();
        }
        return new FormatStringImpl(stringValues, objectValues != null ? objectValues : InternalUtil.$EMPTY_OBJECT_ARRAY);
    }
}
