package banana.builtin;

import java.math.BigInteger;

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

    public static Int valueOf(BigInteger value) {
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

    @Override
    public int intValue() {
        return biValue == null ? iValue : biValue.intValue();
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

    @Override
    public String toString() {
        return biValue == null ? Integer.toString(iValue) : biValue.toString();
    }
}
