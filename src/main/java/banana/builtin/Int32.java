package banana.builtin;

public final class Int32 extends Number {
    private static final Int32[] INTERNED;
    private static Int32 cache1, cache2;

    static {
        INTERNED = new Int32[256];
        for (int i = -128; i < 128; i++) {
            INTERNED[i + 128] = new Int32(i);
        }
        cache1 = cache2 = INTERNED[128];
    }

    private final int value;

    private Int32(int value) {
        this.value = value;
    }

    public static Int32 valueOf(int value) {
        if (value >= -128 && value < 128) {
            return INTERNED[value + 128];
        }
        if (value == cache1.value) {
            return cache1;
        }
        if (value == cache2.value) {
            Int32 result = cache2;
            cache2 = cache1;
            return cache1 = result;
        }
        cache2 = cache1;
        return cache1 = new Int32(value);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public Int32 add(Number other) {
        return valueOf(value + other.intValue());
    }
}
