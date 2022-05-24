package banana.builtin;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import banana.internal.annotation.ExtensionMethod;
import banana.internal.annotation.NonNull;
import banana.internal.annotation.Nullable;
import banana.internal.util.InternalUtil;

public final class StringExtensions {
    private StringExtensions() {
    }

    @ExtensionMethod
    @NonNull
    public static String add(@NonNull final String thiz, @Nullable Object other) {
        return thiz.concat(String.valueOf(other));
    }

    @ExtensionMethod
    @NonNull
    public static String multiply(@NonNull final String thiz, int count) {
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

    @ExtensionMethod
    @NonNull
    public static FormatString toFormatString(@NonNull final String thiz) {
        return InternalUtil.$trustedFormatString(new String[] {thiz}, InternalUtil.$EMPTY_OBJECT_ARRAY);
    }
}
