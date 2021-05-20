package ArraysListsStacksQueuesDeques;

public class ArrayQueue {
    private int[] arr;
    private int ePosition;
    private int dPosition;
    private int size;

    /**
     * Creates a new ArrayQueue with the given capacity.
     * @param capacity the capacity for this queue
     */
    public ArrayQueue(int capacity) {
        arr = new int[capacity];
        ePosition = 0;
        dPosition = 0;
        size = 0;
        // TODO
    }

    /**
     * Adds the given element to the queue.
     * @param e the element to add to the queue
     * @throws FullQueueException if the queue is full
     */
    public void enqueue(int e) throws FullQueueException {
        if(size == arr.length)
            throw new FullQueueException();
        arr[ePosition] = e;
        if(ePosition == arr.length-1){
            ePosition = 0;
        } else {
            ePosition++;
        }
        size++;
        // TODO
    }

    /**
     * Removes an element from the queue and returns it.
     * @return the first element in the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public int dequeue() throws EmptyQueueException {
        if(size == 0)
            throw new EmptyQueueException();
        int result = arr[dPosition];
        arr[dPosition] = 0;
        if(dPosition == arr.length-1){
            dPosition = 0;
        } else {
            dPosition++;
        }
        size--;
        return result;
        // TODO
    }
}

class EmptyQueueException extends RuntimeException {

    private static final long serialVersionUID = 42L;
}

class FullQueueException extends RuntimeException {

    private static final long serialVersionUID = 42L;
}