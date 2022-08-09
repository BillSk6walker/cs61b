public class ArrayDeque<T> {
    private int size;
    private int max;//how many things at most can be put in the array
    private int headpointer;//points at the place to save(head)
    private int tailpointer;//points at the place to save(tail)
    private T[] items;
    private double usagefactor;//saves how many memory has been used of the array(should be bigger than 25%)

    /**
     * Resize the array if it's full
     */
    private void checkResize() {
        if (size == max) {
            T[] temp = (T[]) new Object[max * 2];
            for (int i = 0; i < size; i++) {
                headpointer += 1;
                if (headpointer == max) {
                    headpointer = 0;
                }
                temp[i] = items[headpointer];
            }
            max *= 2;
            headpointer = max - 1;
            tailpointer = size;
            items = temp;
        } else {
            usagefactor = (double) size / (double) max;
            if (usagefactor < 0.3 && max > 8) {
                T[] temp = (T[]) new Object[max / 2];
                for (int i = 0; i < size; i++) {
                    headpointer += 1;
                    if (headpointer == max) {
                        headpointer = 0;
                    }
                    temp[i] = items[headpointer];
                }
                max /= 2;
                headpointer = max - 1;
                tailpointer = size + 1;
                items = temp;
            }

        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        checkResize();
        items[headpointer] = item;
        headpointer -= 1;
        if (headpointer < 0) {
            headpointer = max - 1;
        }
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        checkResize();
        items[tailpointer] = item;
        tailpointer += 1;
        if (tailpointer == max) {
            tailpointer = 0;
        }
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int iterator = headpointer + 1;
        if (iterator == max) {
            iterator = 0;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(items[iterator]);
            System.out.print(" ");
            iterator += 1;
            if (iterator == max) {
                iterator = 0;
            }
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            headpointer += 1;
            if (headpointer == max) {
                headpointer = 0;
            }
            T ret = items[headpointer];
            size -= 1;
            checkResize();
            return ret;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            tailpointer -= 1;
            if (tailpointer < 0) {
                tailpointer = max - 1;
            }
            T ret = items[tailpointer];
            checkResize();
            size -= 1;
            return ret;
        }
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (index > size) {
            return null;
        }
        if (headpointer + index + 1 < max) {
            return items[headpointer + index + 1];
        } else {
            return items[index - max + headpointer + 1];
        }
    }


    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        size = 0;
        headpointer = 0;
        tailpointer = 1;
        max = 8;
        items = (T[]) new Object[max];
    }

}
