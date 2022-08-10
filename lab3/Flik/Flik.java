import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * An Integer tester created by Flik Enterprises.
 */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }

    @Test
    public void TestIsSameNumber() {
        int a = 128;
        int b = 128;
        assertTrue(isSameNumber(a, b));
    }
}
