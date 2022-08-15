package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<Double>(10);
        arb.enqueue(3.2);
        arb.enqueue(5.23);
        assertEquals(3.2,(double) arb.dequeue(),0.01);
        arb.enqueue(8.32);
        arb.enqueue(123.3);
        assertEquals(5.23,(double) arb.dequeue(),0.01);
        assertEquals(8.32,(double) arb.peek(),0.01);
        arb.enqueue(23.3);
        assertEquals(8.32,(double) arb.dequeue(),0.01);
        assertEquals(123.3,(double) arb.dequeue(),0.01);
        assertEquals(23.3,(double) arb.dequeue(),0.01);
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
