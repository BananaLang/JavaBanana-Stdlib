package banana.builtin;

import banana.internal.annotation.BananaModule;
import banana.internal.annotation.NonNull;
import banana.internal.annotation.Nullable;

@BananaModule(
    exportedClasses = {
        Int.class,
        Int32.class
    },
    publicImports = {
        "banana/builtin/StringExtensions.*"
    }
)
public final class ModuleBuiltin {
    public static final int JVM_VERSION;

    static {
        String jvmVersionStr = System.getProperty("java.specification.version");
        if (jvmVersionStr.startsWith("1.")) {
            JVM_VERSION = Integer.parseInt(jvmVersionStr.substring(2));
        } else {
            JVM_VERSION = Integer.parseInt(jvmVersionStr);
        }
    }

    private ModuleBuiltin() {
    }

    public static void print(boolean b) {
        System.out.print(b);
    }

    public static void print(char c) {
        System.out.print(c);
    }

    public static void print(int i) {
        System.out.print(i);
    }

    public static void print(long l) {
        System.out.print(l);
    }

    public static void print(float f) {
        System.out.print(f);
    }

    public static void print(double d) {
        System.out.print(d);
    }

    public static void print(@NonNull char[] s) {
        System.out.print(s);
    }

    public static void print(@Nullable String s) {
        System.out.print(s);
    }

    public static void print(@Nullable Object obj) {
        System.out.print(obj);
    }

    public static void println() {
        System.out.println();
    }

    public static void println(boolean x) {
        System.out.println(x);
    }

    public static void println(char x) {
        System.out.println(x);
    }

    public static void println(int x) {
        System.out.println(x);
    }

    public static void println(long x) {
        System.out.println(x);
    }

    public static void println(float x) {
        System.out.println(x);
    }

    public static void println(double x) {
        System.out.println(x);
    }

    public static void println(@NonNull char[] x) {
        System.out.println(x);
    }

    public static void println(@Nullable String x) {
        System.out.println(x);
    }

    public static void println(@Nullable Object x) {
        System.out.println(x);
    }
}
