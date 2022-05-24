package banana.builtin;

import banana.internal.annotation.NonNull;

public interface FormatString {
    @NonNull
    String[] stringValues();
    @NonNull
    Object[] objectValues();
    @NonNull
    String rawString();
}
