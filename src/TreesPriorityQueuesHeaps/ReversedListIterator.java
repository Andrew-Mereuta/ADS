package TreesPriorityQueuesHeaps;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterates lazily over lists in reversed order. For instance, the list
 * [1,2,3,4] should be iterated as follows: 4 -> 3 -> 2 -> 1.
 */
class ReversedListIterator<V> implements Iterator<V> {

    /**
     * Constructor.
     * Should reset on a new List.
     *
     * @param list
     *     takes the list
     */
    private List<V> list;
    private int i;

    public ReversedListIterator(List<V> list) {
        this.list = list;
        i=list.size()-1;
        // TODO
    }

    /**
     * @return True if there is a next element in the iterator, else False
     */
    @Override
    public boolean hasNext() {
        return i >= 0 && !list.isEmpty();
        // TODO
    }

    /**
     * Get the next element of the iterator and shift
     * iterator by one.
     *
     * @return current element value
     * @post iterator is moved to next element
     * @throws NoSuchElementException
     *      iff there is no next element
     */
    @Override
    public V next() throws NoSuchElementException {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        V result = list.get(i);
        i--;
        return result;
        // TODO
    }

    /**
     * Skip a single element of the iterator.
     *
     * @post iterator is moved to next element
     * @throws NoSuchElementException
     *      iff there is no elemented to be removed
     */
    @Override
    public void remove() throws NoSuchElementException {
        if(list.isEmpty()){
            throw new NoSuchElementException();
        }
        i--;
        // TODO
    }
}
