package ArraysListsStacksQueuesDeques;

import java.util.ArrayList;

public class Solution {
    /**
     * Remove last occurrence in array
     *
     * Takes the array and the last occurring element x,
     * shifting the rest of the elements left. I.e.
     * [1, 4, 7, 9], with x=7 would result in:
     * [1, 4, 9].
     *
     * @param x
     *     the entry to remove from the array
     * @param arr
     *     to remove an entry from
     * @return the updated array, without the last occurrence of x
     */
    public int[] removeLastOccurrence(int x, int[] arr) {
        // TODO
        if(arr.length==0){
            return new int[0];
        }
        boolean tr = false;
        for(int i =0; i < arr.length;i++){
            if(x==arr[i])
                tr= true;
        }
        if(tr) {
            int j = arr.length - 2;
            int[] nArr = new int[arr.length - 1];
            for (int i = arr.length - 1; i >= 0; i--) {
                if (x != arr[i] && tr) {
                    nArr[j] = arr[i];
                    j--;
                } else if (!tr) {
                    nArr[j] = arr[i];
                    j--;
                } else tr = false;

            }
            return nArr;
        }
        else return arr;
    }

    /**
     * Takes an ArrayList and removes last occurrence of x,
     * shifting the rest of the elements left.
     * I.e. [5, 1, 5, 9, 8], with x = 5
     * would result in: [5, 1, 9, 8].
     * Note that this method does not return a new list.
     * Instead, the list that is passed as a parameter is changed.
     *
     * @param list
     *     to remove an element from.
     * @param x
     *     element value to look for
     */
    public void removeLastOccurrence(int x, ArrayList<Integer> list) {
        // TODO
        if(!list.isEmpty()){
            for(int i=list.size()-1; i>=0;i--){
                if(list.get(i)==x){
                    list.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Clones array
     *
     * @param a array
     * @return cloned array
     */
    public double[][] clone(double[][] a) {
        double[][] b =  new double[a.length][];
        // TODO
        for(int i=0;i<a.length;i++){
            b[i]= new double[a[i].length];
            for(int j=0;j<a[i].length;j++){
                b[i][j]= a[i][j];
            }
        }
        return b;
    }
}
