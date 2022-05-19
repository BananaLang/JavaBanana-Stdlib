package banana.builtin;

import java.math.BigInteger;

import banana.internal.annotation.NonNull;

public final class Int extends Number {
    private static final Int[] INTERNED;
    private static final BigInteger
        INT_MIN = BigInteger.valueOf(Integer.MIN_VALUE),
        INT_MAX = BigInteger.valueOf(Integer.MAX_VALUE);
    private static Int iCache1, iCache2;
    private static Int biCache1, biCache2;

    static {
        INTERNED = new Int[256];
        for (int i = -128; i < 128; i++) {
            INTERNED[i + 128] = new Int(i);
        }
        iCache1 = iCache2 = INTERNED[128];
        biCache1 = biCache2 = new Int(BigInteger.ZERO);
    }

    private final int iValue;
    private final BigInteger biValue;

    private Int(int value) {
        iValue = value;
        biValue = null;
    }

    private Int(BigInteger value) {
        iValue = 0;
        biValue = value;
    }

    @NonNull
    public static Int valueOf(long value) {
        if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE) {
            return valueOf((int)value);
        }
        return valueOf(BigInteger.valueOf(value));
    }

    @NonNull
    public static Int valueOf(int value) {
        if (value >= -128 && value < 128) {
            return INTERNED[value + 128];
        }
        if (value == iCache1.iValue) {
            return iCache1;
        }
        if (value == iCache2.iValue) {
            Int result = iCache2;
            iCache2 = iCache1;
            return iCache1 = result;
        }
        iCache2 = iCache1;
        return iCache1 = new Int(value);
    }

    @NonNull
    public static Int valueOf(@NonNull BigInteger value) {
        if (value.compareTo(INT_MIN) >= 0 && value.compareTo(INT_MAX) <= 0) {
            return valueOf(value.intValue());
        }
        if (value.equals(biCache1.biValue)) {
            return biCache1;
        }
        if (value.equals(biCache2.biValue)) {
            Int result = biCache2;
            biCache2 = biCache1;
            return biCache1 = result;
        }
        biCache2 = biCache1;
        return biCache1 = new Int(value);
    }

    @NonNull
    public Int add(@NonNull Int other) {
        return valueOf(toBigInteger().add(other.toBigInteger()));
    }

    @NonNull
    public Int subtract(@NonNull Int other) {
        return valueOf(toBigInteger().subtract(other.toBigInteger()));
    }

    @NonNull
    public Int multiply(@NonNull Int other) {
        return valueOf(toBigInteger().multiply(other.toBigInteger()));
    }

    @NonNull
    public Int divide(@NonNull Int other) {
        return valueOf(toBigInteger().divide(other.toBigInteger()));
    }

    @NonNull
    public Int remainder(@NonNull Int other) {
        return valueOf(toBigInteger().remainder(other.toBigInteger()));
    }

    @NonNull
    public Int or(@NonNull Int other) {
        return valueOf(toBigInteger().or(other.toBigInteger()));
    }

    @NonNull
    public Int xor(@NonNull Int other) {
        return valueOf(toBigInteger().xor(other.toBigInteger()));
    }

    @NonNull
    public Int and(@NonNull Int other) {
        return valueOf(toBigInteger().and(other.toBigInteger()));
    }

    @NonNull
    public Int shiftLeft(@NonNull Int other) {
        if (other.biValue != null) {
            throw new IllegalArgumentException("<< RHS must be -2147483648 <= n <= 2147483647");
        }
        return valueOf(toBigInteger().shiftLeft(other.iValue));
    }

    @NonNull
    public Int shiftRight(@NonNull Int other) {
        if (other.biValue != null) {
            throw new IllegalArgumentException(">> RHS must be -2147483648 <= n <= 2147483647");
        }
        return valueOf(toBigInteger().shiftRight(other.iValue));
    }

    public boolean truthy() {
        return biValue != null || iValue != 0;
    }

    @Override
    public int intValue() {
        return biValue == null ? iValue : biValue.intValue();
    }

    public int intValueExact() {
        if (biValue != null) {
            throw new ArithmeticException("Int requires more that 32 bits to represent");
        }
        return iValue;
    }

    public long longValueExact() {
        return biValue == null ? iValue : biValue.longValueExact();
    }

    @Override
    public long longValue() {
        return biValue == null ? iValue : biValue.longValue();
    }

    @Override
    public float floatValue() {
        return biValue == null ? iValue : biValue.floatValue();
    }

    @Override
    public double doubleValue() {
        return biValue == null ? iValue : biValue.doubleValue();
    }

    public BigInteger toBigInteger() {
        return biValue != null ? biValue : BigInteger.valueOf(iValue);
    }

    @Override
    @NonNull
    public String toString() {
        return biValue == null ? Integer.toString(iValue) : biValue.toString();
    }
}
