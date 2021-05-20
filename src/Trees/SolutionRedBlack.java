package Trees;

public class SolutionRedBlack {

    /**
     * Checks whether the given BinaryTree is a Red Black Tree.
     * @param tree BinaryTree to check.
     * @return True if the given tree is a Red Black Tree, false otherwise.
     */
    public static boolean isRedBlackTree(BinaryRedBlackTree tree) {
        return tree.isBlack() && isTreeBST(tree) && blackChildren(tree) && blackDepth(tree) != -1;
        // TODO
    }

    public static int blackDepth(BinaryRedBlackTree tree){
        if(tree == null){
            return 0;
        }
        int h1 = 0;
        int h2 = 0;
        if(tree.hasLeft()){
            h1 = blackDepth(tree.getLeft());
        }
        if(tree.hasRight()){
            h2 = blackDepth(tree.getRight());
        }
        if(h1 == -1 || h2 == -1){
            return -1;
        }
        if(h1!=h2){
            return -1;
        }
        if(tree.isBlack()){
            return h1+1;
        } else {
            return h1;
        }
    }

    public static boolean blackChildren(BinaryRedBlackTree tree){
        if(tree == null){
            return true;
        }
        if(tree.isRed()){
            if(tree.hasLeft()){
                if(tree.getLeft().isRed()){
                    return false;
                }
            }
            if(tree.hasRight()){
                if(tree.getRight().isRed()){
                    return false;
                }
            }
        }
        if(tree.hasLeft()){
            boolean b = blackChildren(tree.getLeft());
            if(!b){
                return false;
            }
        }
        if(tree.hasRight()){
            boolean b = blackChildren(tree.getRight());
            if(!b){
                return false;
            }
        }
        return true;
    }

    public static boolean isTreeBST(BinaryRedBlackTree tree) {
        return help(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean help(BinaryRedBlackTree tree, int min_r, int max_r){
        boolean b = true;
        if(tree.hasLeft()){
            if((tree.getLeft().getValue() > tree.getValue()) || (tree.getLeft().getValue() < min_r)) {
                b = false;
            }
            boolean t = help(tree.getLeft(), min_r, tree.getValue()-1);
            if(!t){
                return t;
            }
        }
        if(tree.hasRight()){
            if((tree.getRight().getValue() < tree.getValue()) || (tree.getRight().getValue() > max_r)) {
                b = false;
            }
            boolean t = help(tree.getRight(), tree.getValue()+1, max_r);
            if(!t){
                return t;
            }
        }
        return b;
    }
}

class BinaryRedBlackTree {

    private int value;

    private BinaryRedBlackTree left, right;

    private boolean isRed;

    /**
     * Simple constructor.
     *
     * @param value Value for this tree set as value.
     * @param isRed True if this node is red, false otherwise.
     */
    public BinaryRedBlackTree(int value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
    }

    /**
     * Extended constructor.
     *
     * @param value to set as value.
     * @param left to set as left child.
     * @param right to set as right child.
     */
    public BinaryRedBlackTree(int value, boolean isRed, BinaryRedBlackTree left, BinaryRedBlackTree right) {
        this(value, isRed);
        setLeft(left);
        setRight(right);
    }

    /**
     * @return the value of this tree.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the new value of this tree.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the left child.
     */
    public BinaryRedBlackTree getLeft() {
        return left;
    }

    /**
     * @return the right child.
     */
    public BinaryRedBlackTree getRight() {
        return right;
    }

    /**
     * @return true if this node is red, false otherwise.
     */
    public boolean isRed() {
        return isRed;
    }

    /**
     * @return true if this node is black, false otherwise.
     */
    public boolean isBlack() {
        return !isRed;
    }

    /**
     * @return True if the tree has a left child, false otherwise.
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * @return True if the tree has a right child, false otherwise.
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * @param left Left subtree to set.
     */
    public void setLeft(BinaryRedBlackTree left) {
        this.left = left;
    }

    /**
     * @param right Right subtree to set.
     */
    public void setRight(BinaryRedBlackTree right) {
        this.right = right;
    }

    /**
     * @param red True if the new color is red, false otherwise.
     */
    public void setRed(boolean red) {
        isRed = red;
    }
}