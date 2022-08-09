public class ArrayDeque<T> {
    int size;
    int max=8;//how many things at most can be put in the array
    int head_pointer;//points at the place to save(head)
    int tail_pointer;//points at the place to save(tail)
    T[] items = (T[]) new Object[max];
    double usage_factor;//saves how many memory has been used of the array(should be bigger than 25%)

    /** Resize the array if it's full*/
    public void  checkResize(){
        if(size==max){
            T[] temp = (T[]) new Object[max*2];
            for(int i=0;i<size;i++){
                head_pointer+=1;
                if(head_pointer==max){
                    head_pointer=0;
                }
                temp[i]=items[head_pointer];
            }
            max*=2;
            head_pointer=max-1;
            tail_pointer=size+1;
            items=temp;
        }
        else{
            usage_factor=(double) size/(double) max;
            if(usage_factor<0.3){
                T[] temp = (T[]) new Object[max/2];
                for(int i=0;i<size;i++){
                    head_pointer+=1;
                    if(head_pointer==max){
                        head_pointer=0;
                    }
                    temp[i]=items[head_pointer];
                }
                max/=2;
                head_pointer=max-1;
                tail_pointer=size+1;
                items=temp;
            }

        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        checkResize();
        items[head_pointer]=item;
        head_pointer-=1;
        if (head_pointer<0){
            head_pointer=max-1;
        }
        size+=1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        checkResize();
        items[tail_pointer]=item;
        tail_pointer+=1;
        if(tail_pointer==max){
            tail_pointer=0;
        }
        size+=1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return  size==0;
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
        int iterator=head_pointer+1;
        if(iterator==max){
            iterator=0;
        }
        for (int i=0;i<size;i++){
            System.out.print(items[iterator]);
            System.out.print(" ");
            iterator+=1;
            if(iterator==max){
                iterator=0;
            }
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if(isEmpty()){
            return  null;
        }
        else{
            head_pointer+=1;
            if(head_pointer==max){
                head_pointer=0;
            }
            T ret=items[head_pointer];
            checkResize();
            return  ret;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if(isEmpty()){
            return  null;
        }
        else{
            tail_pointer-=1;
            if(tail_pointer<0){
                tail_pointer=max-1;
            }
            T ret=items[tail_pointer];
            checkResize();
            return  ret;
        }
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if(head_pointer+index<max){
            return items[head_pointer+index];
        }
        else{
            return items[index+max-head_pointer-1];
        }
    }



    /** Creates an empty array deque. */
    public ArrayDeque(){
        size=0;
        head_pointer=0;
        tail_pointer=1;
    }

}
