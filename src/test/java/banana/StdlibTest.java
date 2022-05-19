package banana;

import java.util.concurrent.ThreadLocalRandom;

import banana.builtin.Int;

public class StdlibTest {
    public static void main(String[] args) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        Int a = Int.valueOf(rand.nextLong());
        Int b = Int.valueOf(rand.nextLong());
        System.out.println(a.shiftLeft(b));
    }
}
