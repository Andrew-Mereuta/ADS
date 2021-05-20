package ArraysListsStacksQueuesDeques;

import java.util.EmptyStackException;

public class ArrayStack {
    private Object[] elements;
    private int size;
    private int capacity;

    /**
     * Creates an empty ArrayStack with capacity 1.
     */
    public ArrayStack() {
        capacity = 1;
        elements = new Object[capacity];
        size=0;
        // TODO
    }

    /**
     * @return The size of this ArrayStack.
     */
    public int size() {
        return size;
        // TODO
    }

    /**
     * @return `true` iff this ArrayStack is empty, `false` otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
        // TODO
    }

    /**
     * @return `true` iff the size is equal to the capacity, `false` otherwise.
     */
    public boolean isFull() {
        return size == capacity;
        // TODO
    }

    /**
     * @return the top element of the stack without removing it
     */
    public Object peek() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return elements[size-1];
        // TODO
    }

    /**
     * Adds `o` to the stack.
     * If capacity of stack was too small, capacity is doubled and `o` is added.
     *
     * @param o
     *     the element to add to the stack.
     */
    public void push(Object o) {
        if(isFull()){
            Object[] tmp = new Object[elements.length*2];
            for(int i=0;i < size; i++){
                tmp[i] = elements[i];
            }
            capacity*=2;
            elements = new Object[capacity];
            for(int i=0;i < size; i++){
                elements[i] = tmp[i];
            }
        }
        elements[size] = o;
        size++;
        // TODO
    }

    /**
     * Removes the top element from the stack.
     * If removing top would make the stack use less than 25% of its capacity,
     * then the capacity is halved.
     *
     * @return the element which was at the top of the stack.
     * @throws EmptyStackException
     *     iff the stack is empty
     */
    public Object pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        Object result = elements[size-1];
        size--;
        if(size < elements.length/4){
            Object[] tmp = new Object[elements.length/2];
            for(int i=0;i < size; i++){
                tmp[i] = elements[i];
            }
            capacity/=2;
            elements = new Object[capacity];
            for(int i=0;i < size; i++){
                elements[i] = tmp[i];
            }
        }
        return result;
        // TODO
    }

    /**
     * @return a String representation of the ArrayStack
     * Example output for ArrayStack with 2 elements and capacity 5:
     * <ArrayStack[1,2]>(Size=2, Cap=5)
     */
    public String toString() {
        String res = "<ArrayStack[";
        for(int i=0; i<size;i++){
            res += elements[i];
            if(i<size-1){
                res+=",";
            }
        }
        res= res + "]>(Size=" + size + ", Cap=" + capacity +")";
        return res;
        // TODO
    }

    // For testing, do not remove or change.
    public Object[] getElements() {
        return elements;
    }
}
