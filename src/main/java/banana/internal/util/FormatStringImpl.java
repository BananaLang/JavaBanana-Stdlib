package banana.internal.util;

import banana.builtin.FormatString;

final class FormatStringImpl implements FormatString {
    private final String[] stringValues;
    private final Object[] objectValues;

    FormatStringImpl(String[] stringValues, Object[] objectValues) {
        this.stringValues = stringValues;
        this.objectValues = objectValues;
    }

    @Override
    public String[] stringValues() {
        return stringValues.clone();
    }

    @Override
    public Object[] objectValues() {
        return objectValues.clone();
    }

    @Override
    public String rawString() {
        StringBuilder result = new StringBuilder(stringValues[0]);
        for (int i = 1; i < stringValues.length; i++) {
            result.append(objectValues[i - 1]).append(stringValues[i]);
        }
        return result.toString();
    }
}
