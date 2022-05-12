package banana.internal.util;

/**
 * All public methods in this class start with a $ to prevent being called from BananaLang
 */
public class InternalUtil {
    public static NullPointerException $nullAssertionFailure(String src) throws NullPointerException {
        throw new NullPointerException("Non-null assertion failed because " + src + " evaluated to null");
    }
}
