package banana.builtin;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import banana.internal.annotation.ExtensionMethod;

public final class StringExtensions {
    private StringExtensions() {
    }

    @ExtensionMethod
    public static String add(String thiz, String other) {
        return thiz.concat(other);
    }

    @ExtensionMethod
    public static String multiply(String thiz, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count is negative: " + count);
        }
        if (count == 1) {
            return thiz;
        }
        final int len = thiz.length();
        if (len == 0 || count == 0) {
            return "";
        }
        if (Integer.MAX_VALUE / count < len) {
            throw new OutOfMemoryError("Required length exceeds implementation limit");
        }
        if (len == 1) {
            final char theChar = thiz.charAt(0);
            if ((theChar & 0xFF) == theChar && ModuleBuiltin.JVM_VERSION >= 9) {
                byte[] single = new byte[len];
                Arrays.fill(single, (byte)theChar);
                return new String(single, StandardCharsets.ISO_8859_1);
            } else {
                char[] single = new char[len];
                Arrays.fill(single, theChar);
                return new String(single);
            }
        }
        StringBuilder result = new StringBuilder(len * count);
        for (int i = 0; i < count; i++) {
            result.append(thiz);
        }
        return result.toString();
    }
}
