package TreesPriorityQueuesHeaps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     * Creates a hard copy of the n-ary tree.
     * @param t - the tree to create a copy of
     * @return a new tree in which every node contains the values of the nodes at the corresponding positions in t
     */
    public Tree copy(Tree t) {
        if(t == null) {
            return null;
        }
        Tree copy = new Tree(t.getValue());
        List<Tree> children = t.getChildren();
        List<Tree> nChildren = new ArrayList<>();
        for(int i =0; i < children.size(); i++ ){
            nChildren.add(copy(children.get(i)));
        }
        copy = new Tree(t.getValue(), nChildren);
        return copy;
        // TODO
    }

    /**
     * Checks if given array is heap.
     *
     * @param arr integer array to be checked (root at index 0)
     * @param n the size of the array to be checked
     * @return true if the array satisfied the heap property, false otherwise
     */
    public boolean isHeap(int[] arr, int n) {
        if(arr == null || n > arr.length)
            return false;
        for(int i=1; i < n;i++) {
            int pIndex = getParent(arr, i);
            if(pIndex!=-1) {
                if(arr[pIndex] < arr[i])
                    return false;
            }
        }
        return true;
        // TODO: Implement method
    }

    /**
     * @param arr array representation of a heap (you may assume it is a valid heap)
     * @param i index of node whose parent we're looking for (make no assumptions about it's validity)
     * @return index of the parent of node i, or -1 if: (a) i is not a valid index, (b) i doesn't have a parent
     */
    public int getParent(int[] arr, int i) {
        if(i<arr.length){
            if(i%2!=0){
                if(i<1) return -1;
                return (i-1)/2;
            } else {
                if(i<2) return -1;
                return (i-2)/2;
            }
        }
        return -1;
        // TODO: Implement method
    }

    /**
     * Sums the values of the nodes of two n-ary trees.
     * @param t1 - first tree to sum values for
     * @param t2 - second tree to sum values for
     * @return a new tree in which every node contains the sum of the values of the nodes at the corresponding positions in t1 and t2
     */
    public Tree sum(Tree t1, Tree t2) {
        if(t1 == null || t2 == null){
            return null;
        }
        if(t1.getChildren() == null || t2.getChildren() == null){
            return new Tree(t1.getValue() + t2.getValue());
        }
        List<Tree> t1Children = t1.getChildren();
        List<Tree> t2Children = t2.getChildren();
        List<Tree> resultChildren = new ArrayList<>();
        for(int i=0; i<t1Children.size(); i++){
            resultChildren.add(sum(t1Children.get(i), t2Children.get(i)));
        }
        return new Tree(t1.getValue() + t2.getValue(), resultChildren);
        // TODO
    }

    /**
     * returns the middle node in the last level of a heap
     *
     * @param heap
     *     the Heap to check, can be null. If not null, this heap will always contain at least one Node.
     * @return the Node corresponding to the middle element in the last layer of the Heap, or null if the Heap is null.
     * In case the last layer contains an even number of elements, returns the element just left of the middle (see test).
     */
    public Heap.Node findMiddleInLastLayer(Heap heap) {
        // TODO
        if(heap==null){
            return null;
        }
        if(heap.size() == 1){
            return heap.getRoot();
        }
        int height = (int) Math.ceil(Math.log(heap.size()+1) / Math.log(2)) -1;//0, 1, 2, 3 -> 3
        int amNodes = (int) Math.pow(2, height) -1;//7
        int lastLayerNodes = heap.size()-amNodes-1;//13-7=6->5
        int desiredIndex = lastLayerNodes/2;// 2

        int mask = 1 << (height -1);//001 010 100
        Heap.Node hp = heap.getRoot();
        while(mask!=0){
            if((desiredIndex & mask) == 0){
                hp = heap.getLeft(hp);
            }
            else{
                hp = heap.getRight(hp);
            }
            mask=  mask>>1;
        }
        return hp;
    }
}

class Heap {
    private int size = 0;
    private Heap.Node root;

    /**
     * Initializes a Heap with one Node.
     *
     * @param rootKey
     *     the key given to the root Node of the Heap.
     */
    public Heap(int rootKey) {
        root = new Heap.Node(rootKey);
        size++;
    }

    /**
     * @return the root Node of this Heap.
     */
    public Heap.Node getRoot() {
        return root;
    }

    /**
     * @param n
     *     The Node to get the left child from.
     * @return the left child of n.
     */
    public Heap.Node getLeft(Heap.Node n) {
        return n.left;
    }

    /**
     * @param n
     *     The Node to get the right child from.
     * @return the right child of n.
     */
    public Heap.Node getRight(Heap.Node n) {
        return n.right;
    }

    /**
     * @param n
     *     The Node to check the left child from.
     * @return true iff Node n has a left child, false otherwise.
     */
    public boolean hasLeft(Heap.Node n) {
        return n.left != null;
    }

    /**
     * @param n
     *     The Node to check the right child from.
     * @return true iff Node n has a right child, false otherwise.
     */
    public boolean hasRight(Heap.Node n) {
        return n.right != null;
    }

    /**
     * This method creates a new left child of n if it does not yet have a left child.
     *
     * @param n
     *     The Node to set the left child from.
     * @param leftKey
     *     The key to set in the left child of Node n.
     */
    public void setLeft(Heap.Node n, int leftKey) {
        if (n.left == null) {
            n.left = new Heap.Node(leftKey);
            size++;
        } else {
            n.left.key = leftKey;
        }
    }

    /**
     * This method creates a new right child of n if it does not yet have a right child.
     *
     * @param n
     *     The Node to set the right child from.
     * @param rightKey
     *     The key to set in the right child of Node n.
     */
    public void setRight(Heap.Node n, int rightKey) {
        if (n.right == null) {
            n.right = new Heap.Node(rightKey);
            size++;
        } else {
            n.right.key = rightKey;
        }
    }

    /**
     * @return The size of this Heap, i.e. the amount of Nodes.
     */
    public int size() {
        return size;
    }

    class Node {
        private int key;
        private Heap.Node left, right;

        /**
         * Simple constructor.
         *
         * @param key
         *     to set as key.
         */
        public Node(int key) {
            this.key = key;
        }

        /**
         * @return The integer key of this Node.
         */
        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key + "(" + (left == null ? " " : left) + ',' + (right == null ? " " : right) + ')';
        }
    }
}


class Tree {

    private int value;

    private List<Tree> children;

    /**
     * Creates a tree with the given value, and no children.
     *
     * @param value The value for this tree.
     */
    public Tree(int value) {
        this.children = new LinkedList<>();
        this.value = value;
    }

    /**
     * Creates a tree with the given value and list of children.
     *
     * @param value    The value for this tree.
     * @param children The subtrees for this tree.
     */
    public Tree(int value, List<Tree> children) {
        this.children = new LinkedList<>(children);
        this.value = value;
    }

    /**
     * Returns the value of this tree.
     *
     * @return The value of this tree.
     */
    public int getValue() {
        return value;
    }


    /**
     * Sets the value of this tree.
     *
     * @param value new value of this tree.
     */
    public int setValue(int value) {
        return this.value = value;
    }

    /**
     * Returns the subtrees of this tree.
     *
     * @return The subtrees of this tree.
     */
    public List<Tree> getChildren() {
        return this.children;
    }

    /**
     * Checks equality of two trees.
     *
     * @param other The object to compare with.
     * @return True if the trees are equal, false otherwise.
     */
    public boolean equals(Object other) {
        if (other instanceof Tree) {
            Tree that = (Tree) other;
            return that.getValue() == this.getValue() && this.getChildren().equals(that.getChildren());
        }
        return false;
    }

    /**
     * Returns a human readable format of this tree.
     *
     * @return A string representation of this tree.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toString(sb, 0);
        return sb.toString();
    }

    /**
     * Returns a human readable format of this tree.
     *
     * @param sb    StringBuilder to append to.
     * @param depth Identation depth of this part.
     */
    private void toString(StringBuilder sb, int depth) {
        for (int i = 0; i < depth; i++) sb.append("  ");
        sb.append("<Tree ").append(this.value).append(" [");

        if (this.children.isEmpty()) {
            sb.append("]>");
            return;
        }
        // Recursively add children
        for (Tree t : this.children) {
            sb.append("\n");
            for (int i = 0; i < depth; i++) sb.append("  ");
            t.toString(sb, depth + 1);
        }
        sb.append("\n");
        for (int i = 0; i < depth; i++) sb.append("  ");
        sb.append("]>");
    }
}