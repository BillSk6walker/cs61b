package synthesizer;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        fillCount = 0;
        this.capacity = capacity;
        first=0;
        last=0;
        rb = (T[])new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("The queue is already full!");
        }
        fillCount+=1;
        rb[last]=x;
        last+=1;
        if(last == capacity){
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("The queue is already empty!");
        }
        fillCount-=1;
        T ret = rb[first];
        first+=1;
        if(first == capacity){
            first = 0;
        }
        return ret;
    }


    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }
    private class DequeIterator implements Iterator<T>{
        private int ptr;
        private int cnt;
        public DequeIterator(){
            ptr=first;
            cnt=0;
        }
        @Override
        public boolean hasNext() {
            return cnt<fillCount;
        }
        @Override
        public T next() {
            cnt++;
            T ret =rb[ptr];
            ptr++;
            if(ptr == capacity){
                ptr=0;
            }
            return ret;
        }
    }
    @Override
    public Iterator<T> iterator(){
        return new DequeIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
