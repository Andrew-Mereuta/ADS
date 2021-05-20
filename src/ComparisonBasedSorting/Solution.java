package ComparisonBasedSorting;

import java.util.*;

public class Solution {

    /**
     * Sorts array using insertion sort
     *
     * @param elements
     *     - array of integers to be sorted.
     */
    public void insertionSort(int[] elements) {
        for(int i=1;i<elements.length;i++){
            int j = i;
            while(j>0 && elements[j]<elements[j-1] ){
                int tmp = elements[j];
                elements[j] = elements[j-1];
                elements[j-1] = tmp;
                j--;
            }
        }
        // TODO
    }

    /**
     * Sorts elements with selection sort
     *
     * @param elements
     *     Array of integers to be sorted.
     */
    public void selectionSort(int[] elements) {
        for(int i=0; i< elements.length; i++){
            int index = findMin(elements, i);
            int tmp = elements[i];
            elements[i] = elements[index];
            elements[index] = tmp;
        }


        // TODO
    }

    public int findMin(int[] elements, int lowerBound) {
        int min = elements[lowerBound];
        int mIndex = lowerBound;
        for(int i = lowerBound; i<elements.length;i++){
            if(min>elements[i]){
                min = elements[i];
                mIndex = i;
            }
        }
        return mIndex;
    }

    /**
     * Sorts array with merge sort
     *
     * @param elements
     *     Array of integers to be sorted.
     * @return
     *     New array of sorted integers.
     */
    public int[] mergeSort(int[] elements) {
        if(elements.length > 1){
            int len = elements.length - elements.length/2;
            int[] arr1 = new int[elements.length/2];
            int[] arr2 = new int[len];

            for(int i=0;i<arr1.length;i++){
                arr1[i] = elements[i];
            }
            for(int i = 0 ;i<arr2.length;i++){
                arr2[i] = elements[arr1.length + i];
            }

            if(arr1.length>1){
                arr1 = mergeSort(arr1);
            }
            if(arr2.length>1){
                arr2 = mergeSort(arr2);
            }
            return merge(arr1, arr2);
        } else {
            return elements;
        }
        // TODO
    }

    public int[] merge(int[] arr1, int[] arr2) {
        int i=0, j=0;
        int r=0;
        int[] result = new int[arr1.length+ arr2.length];
        while(i<arr1.length && j<arr2.length){
            if(arr1[i] < arr2[j]){
                result[r] = arr1[i];
                i++;
            } else {
                result[r] = arr2[j];
                j++;
            }
            r++;
        }

        while(i < arr1.length){
            result[r] = arr1[i];
            i++;
            r++;
        }

        while(j < arr2.length){
            result[r] = arr2[j];
            j++;
            r++;
        }
        return result;
    }

    /**
     * Merges two sorted queues
     *
     * @param queue1 first sorted Queue to be merged
     * @param queue2 second sorted Queue to be merged
     * @return sorted Queue containing all elements from both Queues
     */
    public static MyQueue<Integer> merge(MyQueue<Integer> queue1, MyQueue<Integer> queue2) {
        MyQueue<Integer> copy1 = new MyQueue<>();
        MyQueue<Integer> copy2 = new MyQueue<>();
        MyQueue<Integer> result = new MyQueue<>();
        if(queue1 == null && queue2 == null){
            return null;
        }
        if(queue1==null || queue1.isEmpty()){
            while(!queue2.isEmpty()){
                result.enqueue(queue2.front());
                copy2.enqueue(queue2.dequeue());
            }
            while(!copy2.isEmpty())
                queue2.enqueue(copy2.dequeue());
            return result;
        }
        else if(queue2==null || queue2.isEmpty()){
            while(!queue1.isEmpty()){
                result.enqueue(queue1.front());
                copy1.enqueue(queue1.dequeue());
            }
            while(!copy1.isEmpty())
                queue1.enqueue(copy1.dequeue());
            return result;
        }
        else if(queue1.isEmpty() && queue2.isEmpty()){
            return result;
        }
        else if(queue1==queue2){
            while(!queue1.isEmpty()){
                result.enqueue(queue1.front());
                result.enqueue(queue1.front());
                copy1.enqueue(queue1.dequeue());
            }
            while(!copy1.isEmpty())
                queue1.enqueue(copy1.dequeue());
            return result;
        }
        else{
            while(!queue1.isEmpty() && !queue2.isEmpty()){
                if(queue1.front() > queue2.front()){
                    copy1.enqueue(queue1.front());
                    result.enqueue(queue1.dequeue());
                } else {
                    copy2.enqueue(queue2.front());
                    result.enqueue(queue2.dequeue());
                }
            }

            while(!queue1.isEmpty()){
                copy1.enqueue(queue1.front());
                result.enqueue(queue1.dequeue());
            }

            while(!queue2.isEmpty()){
                copy2.enqueue(queue2.front());
                result.enqueue(queue2.dequeue());
            }

            while(!copy1.isEmpty()){
                queue1.enqueue(copy1.dequeue());
            }

            while(!copy2.isEmpty()){
                queue2.enqueue(copy2.dequeue());
            }

            return result;
            // TODO
        }
    }

    /**
     * Takes a list and returns a new list sorted in descending order.
     * This is done by using the priority queue `queue`.
     *
     * Return null if list is null.
     *
     * @param list
     *     the array that needs to be sorted.
     */
    public List<Integer> pqSort(List<Integer> list) {
        LibraryPQ queue = new SolutionPQ();
        if(list == null){
            return null;
        }
        for(int i =0; i<list.size(); i++){
            queue.insert(list.get(i));
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<list.size();i++){
            result.add(queue.removeMax());
        }
        return result;
        // TODO
    }

    /**
     * Computes how fast the given tasks can be finished by the given amount of students.
     *
     * @param durations Array containing the duration for each tasks.
     * @param n Amount of TAs to complete the tasks.
     * @return The shortest time in which all tasks can be completed.
     */
    public int completeTasks(int[] durations, int n) {
        Queue<Integer> pq = new PriorityQueue<>();
        if(n==0 || durations.length<=0){
            return 0;
        } else {
            if(n > durations.length){
                n = durations.length;
            }
            int j =0;
            for(;j<n;j++){
                pq.add(durations[j]);
            }
            while(j<durations.length){
                int tmp = pq.poll() + durations[j];
                pq.add(tmp);
                j++;
            }
            while(pq.size()!=1){
                pq.poll();
            }
            return pq.poll();
        }
        // TODO
    }

    /**
     * In-place quick sort algorithm
     *
     * @param elements
     *     Array of integers to be sorted.
     */
    public void quickSort(int[] elements) {
        if(elements.length == 0){
            return;
        }
        if(elements.length == 1){
            return;
        }
        int pivot = elements[elements.length-1];
        int sLen =0;
        int bLen =0;
        int eLen =0;
        for(int i = 0; i < elements.length;i++){
            if(elements[i] < pivot){
                sLen++;
            } else if (elements[i] == pivot) {
                eLen++;
            } else {
                bLen++;
            }
        }
        int[] sArr = new int[sLen];
        int[] bArr = new int[bLen];
        int[] eArr = new int[eLen];
        int s=0;
        int b=0;
        int e=0;
        for(int i = 0; i < elements.length;i++){
            if(elements[i] < pivot){
                sArr[s] = elements[i];
                s++;
            } else if (elements[i] == pivot) {
                eArr[e] = elements[i];
                e++;
            } else {
                bArr[b] = elements[i];
                b++;
            }
        }
        if(sArr.length>1) {
            quickSort(sArr);
        }
        if(bArr.length>1) {
            quickSort(bArr);
        }
        s=0;
        for(;s<sArr.length;s++){
            elements[s] = sArr[s];
        }
        e=0;
        for(;e<eArr.length;e++){
            elements[sArr.length+e] = eArr[e];
        }
        b=0;
        for(;b<bArr.length;b++){
            elements[sArr.length+eArr.length+b] = bArr[b];
        }

        // TODO
    }
}


class SolutionPQ extends LibraryPQ {
    /**
     * Restores the heap property in a heap represented as an arraylist.
     * The method compares the node to its parent and swaps if necessary.
     *
     * @param i
     *     index of the node
     */
    @Override
    public void upHeap(int i) {
        if(i==0) return;
        int pIndex = 0;
        if(i%2!=0){
            pIndex = (i-1)/2;
        } else {
            pIndex = (i-2)/2;
        }
        int pValue = getInHeap(pIndex);
        int cValue = getInHeap(i);
        if(cValue > pValue) {
            swap(i, pIndex);
            upHeap(pIndex);
        }
    }

}

abstract class LibraryPQ {
    private List<Integer> heap;

    public LibraryPQ() {
        heap = new ArrayList<>();
    }

    /**
     * Swaps two elements in an array.
     *
     * @param i
     *     Position of element to swap in a.
     * @param j
     *     Position of element to swap in a.
     */
    public void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    /**
     * Restores the heap property in a heap represented as an arraylist.
     * When the heap property is invalid at root,
     * the method fixes the heap first locally before fixing the affected subtree.
     *
     * @param root
     *     Index of the root of the heap, which might be a subtree of the overall heap.
     * @param range
     *     Index of the last element in the heap, array elements with an index > range are not part of the heap.
     */
    public void downHeap(int root, int range) {
        // index of left and right children
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int largest;
        if (left <= range && heap.get(left) > heap.get(root))
            largest = left;
        else
            largest = root;
        if (right <= range && heap.get(right) > heap.get(largest))
            largest = right;
        // heap property invalid at root
        if (largest != root) {
            swap(root, largest);
            downHeap(largest, range);
        }
    }

    /**
     * Restores the heap property in a heap represented as an arraylist.
     * The method compares the node to its parent and swaps if necessary.
     *
     * @param i
     *     index of the node
     */
    public abstract void upHeap(int i);

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param i
     *     element to add
     */
    public void insert(int i) {
        heap.add(i);
        upHeap(heap.size() - 1);
    }

    /**
     * Retrieves and removes the first element of this priority queue.
     *
     * @return the first element of the queue
     */
    public int removeMax() {
        int i = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downHeap(0, heap.size() - 1);
        return i;
    }

    /**
     * Returns the element at the i-th position in the heap.
     *
     * @param i
     *     the index of the position
     * @return element with index i
     */
    public int getInHeap(int i) {
        return heap.get(i);
    }

    /**
     * Checks if another heap represented as a list is equal to this heap.
     *
     * @param l
     *     list representing a heap
     * @return true iff the two lists are equal
     */
    public boolean heapEquals(List<Integer> l) {
        return this.heap.equals(l);
    }
}

class MyQueue<T> {

    private LinkedList<T> q;

    public MyQueue() {
        this.q = new LinkedList<>();
    }

    public void enqueue(T e) {
        q.add(e);
    }

    public T dequeue() {
        return q.poll();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public T front() {
        return q.peek();
    }
}

