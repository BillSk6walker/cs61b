import org.junit.Test;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        a.addLast(0);//0
        a.removeLast()  ;    //
        a.addLast(2);//2
        a.addFirst(3);//3 2
        a.addLast(4);//3 2 4
        a.addFirst(5);//5 3 2 4
        a.addLast(6);//5 3 2 4 6
        a.get(4)     ;//6
        a.addLast(8);//5 3 2 4 6 8
        a.addFirst(9);//9 5 3 2 4 6 8
        a.addLast(10);//9 5 3 2 4 6 8 10
        a.removeLast();//9 5 3 2 4 6 8
        org.junit.Assert.assertEquals(8, (long) a.removeLast());
        ArrayDeque<Integer> b = new ArrayDeque<Integer>();
        b.addLast(0);//0
        b.addLast(1);//0 1
        b.addLast(2); //0 1 2
        b.addLast(3);//0 1 2 3
        b.addFirst(4);//4 0 1 2 3
        b.addLast(5);//4 0 1 2 3 5
        b.get(5)      ;//==> 5
        b.addLast(7);//4 0 1 2 3 5 7
        b.addLast(8);//4 0 1 2 3 5 7 8
        b.addLast(9);//4 0 1 2 3 5 7 8 9
        b.addLast(10);//4 0 1 2 3 5 7 8 9 10
        b.removeLast()      ;//==> 10
        b.removeFirst()    ; //==> 4
        b.removeFirst()   ;  //==> 0
        b.removeFirst()  ;   //==> 1
        b.removeFirst() ;    //==> 2
        b.removeFirst();     //==> 3
        org.junit.Assert.assertEquals(9, (long) b.removeLast());
    }

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     * <p>
     * && is the "and" operation.
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");


        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
    }
}
