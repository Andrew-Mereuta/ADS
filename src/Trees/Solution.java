package Trees;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Return all elements in the given BST in descending order.
     * @param tree The BST to traverse.
     * @return A list of all elements in reverse order.
     */
    public List<Integer> descendingOrder(BinaryTree tree) {
        if(tree == null){
            return null;
        }
        List<Integer> result = new ArrayList<>();
        if(tree.hasRight()){
            List<Integer> tmp = descendingOrder(tree.getRight());
            result.addAll(tmp);
        }
        result.add(tree.getKey());
        if(tree.hasLeft()){
            List<Integer> tmp = descendingOrder(tree.getLeft());
            result.addAll(tmp);
        }
        return result;
        // TODO
    }

    /**
     * Computes whether the BinaryTree is a binary search tree.
     *
     * @param tree
     *     the BinaryTree to check.
     * @return true iff the BinaryTree is a binary search tree, else false.
     */
    public static boolean isTreeBST(BinaryTree tree) {
        if(tree == null){
            return true;
        }
        List<Integer> values = new ArrayList<>();
        return checkBST(tree, values, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // TODO
    }

    public static boolean checkBST(BinaryTree tree, List<Integer> values, int lowerBound, int upperBound){
        if(values.contains(tree.getKey()) || tree.getKey() > upperBound || tree.getKey() < lowerBound ){
            return false;
        } else {
            values.add(tree.getKey());
        }
        if(tree.hasLeft()){
            boolean b = checkBST(tree.getLeft(), values, lowerBound, tree.getKey()-1);
            if(!b){
                return false;
            }
        }
        if(tree.hasRight()){
            boolean b = checkBST(tree.getRight(), values, tree.getKey()+1, upperBound);
            if(!b){
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks whether the given tree has the height-balance property.
     *
     * @param tree
     *     the tree to check.
     * @return true iff the tree has the height-balance property, false otherwise.
     */
    public static boolean isTreeBalanced(BinaryTree tree) {
        if(tree == null){
            return true;
        }
        if(Math.abs(height(tree.getLeft())-height(tree.getRight())) > 1){
            return false;
        } else {
            boolean b = isTreeBalanced(tree.getLeft());
            if(!b){
                return false;
            }
            b = isTreeBalanced(tree.getRight());
            if(!b){
                return false;
            }
            return true;
        }
        // TODO
    }

    public static int height(BinaryTree tree) {
        if(tree == null){
            return 0;
        }
        int h1 = 1;
        if(tree.hasLeft()){
            h1 += height(tree.getLeft());
        }
        int h2 = 1;
        if(tree.hasRight()){
            h2 += height(tree.getRight());
        }
        if(h2 > h1) {
            return h2;
        } else {
            return h1;
        }
    }
}

class BinaryTree {

    private int key;

    private BinaryTree left, right;

    /**
     * Simple constructor.
     *
     * @param key
     *     to set as key.
     */
    public BinaryTree(int key) {
        this.key = key;
    }

    /**
     * Extended constructor.
     *
     * @param key
     *     to set as key.
     * @param left
     *     to set as left child.
     * @param right
     *     to set as right child.
     */
    public BinaryTree(int key, BinaryTree left, BinaryTree right) {
        this.key = key;
        setLeft(left);
        setRight(right);
    }

    public int getKey() {
        return key;
    }

    /**
     * @return the left child.
     */
    public BinaryTree getLeft() {
        return left;
    }

    /**
     * @return the right child.
     */
    public BinaryTree getRight() {
        return right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    /**
     * @param left
     *     to set
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * @param right
     *     to set
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }
}