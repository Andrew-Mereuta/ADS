package KeyBasedSortingHashingMapsSets;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    /**
     * Bucket-sort
     *
     * @param array to be sorted
     * @return sorted array
     */
    @SuppressWarnings("unchecked")
    public Queue<Integer>[] fillBuckets(int[] array) {
        if(array.length==0){
            Queue<Integer>[] buckets = new Queue[0];
            return buckets;
        }
        int vmin = array[0];
        int vmax = array[0];
        for(int i=0; i < array.length; i++){
            if(vmin > array[i]){
                vmin = array[i];
            }
            if(vmax < array[i]){
                vmax = array[i];
            }
        }
        Queue<Integer>[] buckets = new Queue[vmax - vmin + 1];
        for(int i =0; i<buckets.length;i++){
            buckets[i] = new PriorityQueue<>();
        }
        for(int i=0; i < array.length; i++){
            int index = array[i] - vmin;
            buckets[index].add(array[i]);
        }
        // TODO
        return buckets;
    }

    public static int[] readBuckets(Queue<Integer>[] buckets) {
        int size = 0;
        for(int i =0; i<buckets.length;i++){
            size +=buckets[i].size();
        }
        int[] result = new int[size];

        int j=0;
        for(int i=0;i<buckets.length;i++){
            while(!buckets[i].isEmpty()){
                result[j] = buckets[i].poll();
                j++;
            }
        }
        return result;
        // TODO
    }
}
