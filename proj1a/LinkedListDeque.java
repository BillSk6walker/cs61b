public class LinkedListDeque<T> {
    int size;//the number of items in the deque
    TNode sentinel;//the sentinel node,act both as first and last node

    public class TNode {
        T store;
        TNode next;//points at the next intNode
        TNode prev;//points at the previous intNode

        public TNode(T item, TNode next, TNode prev) {
            store = item;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        TNode next_next = sentinel.next;//next to the item to be added
        TNode to_add = new TNode(item, next_next, sentinel);
        sentinel.next = to_add;
        next_next.prev = to_add;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        TNode prev_prev = sentinel.prev;
        TNode to_add = new TNode(item, sentinel, prev_prev);
        sentinel.prev = to_add;
        prev_prev.next = to_add;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size==0;
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
        TNode iterator = sentinel.next;
        while (iterator != sentinel) {
            System.out.print(iterator.store);
            System.out.print(" ");
            iterator = iterator.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T ret = sentinel.next.store;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
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
            T ret = sentinel.prev.store;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
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
        if(index>size){//no such item exists
            return null;
        }
        else{//the item must exists
            TNode iterator=sentinel;
            for (int i=0;i<index;i++){
                iterator=iterator.next;
            }
            return iterator.store;
        }
    }

    /**Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if(index>size){//no such item exists
            return null;
        }
        else {
            return  helpGetRecursive(index,sentinel);
        }
    }

    /**help function getRecursive*/
    public  T helpGetRecursive(int index,TNode present){
        if (index==0)
        {
            return  present.store;
        }
        else{
            return helpGetRecursive(index-1,present.next);
        }
    }

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);//creates the IntNode
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

}
