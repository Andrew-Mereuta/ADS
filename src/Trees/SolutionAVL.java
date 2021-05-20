package Trees;

public class SolutionAVL {

    /**
     * Computes whether the BinaryTree is an AVL tree.
     *
     * @param tree
     *     the BinaryTree to check.
     * @return true iff the BinaryTree is an AVL tree, else false.
     */
    public static boolean isTreeAVL(BinaryTree tree) {
        return isTreeBalanced(tree) && isTreeBST(tree);
        // TODO
    }


    public static int height(BinaryTree tree) {
        if (tree == null) return 0;
        return 1 + Math.max(height(tree.getLeft()), height(tree.getRight()));
    }


    public static boolean isTreeBalanced(BinaryTree tree) {
        // TODO
        int left;
        int right;
        if (tree == null) return true;
        left = height(tree.getLeft());
        right = height(tree.getRight());
        if (Math.abs(left-right) <= 1 && isTreeBalanced(tree.getLeft()) && isTreeBalanced(tree.getRight()))
            return true;
        return false;
    }

    public static boolean isTreeBST(BinaryTree tree) {
        return help(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // TODO
    }

    public static boolean help(BinaryTree tree, int min_r, int max_r){
        boolean b = true;
        if(tree.hasLeft()){
            if((tree.getLeft().getKey() > tree.getKey()) || (tree.getLeft().getKey() < min_r)) {
                b = false;
            }
            boolean t = help(tree.getLeft(), min_r, tree.getKey()-1);
            if(!t){
                return t;
            }
        }
        if(tree.hasRight()){
            if((tree.getRight().getKey() < tree.getKey()) || (tree.getRight().getKey() > max_r)) {
                b = false;
            }
            boolean t = help(tree.getRight(), tree.getKey()+1, max_r);
            if(!t){
                return t;
            }
        }
        return b;
    }
}
