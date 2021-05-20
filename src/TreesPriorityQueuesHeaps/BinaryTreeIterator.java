package TreesPriorityQueuesHeaps;

import java.util.Iterator;
import java.util.Stack;

/**
 * Iterates lazily over a binary tree in a depth-first manner. For instance a tree
 * with 8 as it's root and 4 and 10 as it's children should be iterated as: 8 ->
 * 4 -> 10.
 */
class BinaryTreeIterator<V> implements Iterator<V> {

    private BTree<V> btree;
    private Stack<Position<V>> stack;
    private Position<V> position;
    /**
     * Constructor.
     * Should reset on a new tree.
     *
     * @param tree
     *     takes the tree
     */
    public BinaryTreeIterator(BTree<V> tree) {
        stack = new Stack<>();
        btree = tree;
        if(tree.getRoot() !=null){
            stack.push(btree.getRoot());
            position = btree.getRoot();
        }
        // TODO
    }

    /**
     * @return True if there is a next element in the iterator, else False
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
        // TODO
    }

    /**
     * Get the next element of the iterator and shift
     * iterator by one.
     *
     * @return current element value
     * @post iterator is moved to next element
     */
    @Override
    public V next()  {
        if(hasNext()){
            V value = stack.pop().getValue();
            try{
                if(btree.hasRight(position)){
                    stack.push(btree.getRight(position));
                }
                if(btree.hasLeft(position)){
                    stack.push(btree.getLeft(position));
                    position = btree.getLeft(position);
                } else if(btree.hasRight(position)){
                    position = btree.getRight(position);
                } else {
                    if(!stack.isEmpty()){
                        position = stack.peek();
                    }
                }
            } catch (InvalidPositionException e) {
                return null;
            }
            return value;
        }
        return null;
        // TODO
    }

    /**
     * Skip a single element of the iterator.
     *
     * @post iterator is moved to next element.
     */
    @Override
    public void remove() {
        next();
        // TODO
    }
}

/**
 * DO NOT MODIFY
 */
interface Position<V> {
    /**
     * @return the key of this position.
     */
    public int getKey();

    /**
     * @return the value of the position.
     */
    public V getValue();
}

interface BTree<V> {
    /**
     * @return the root of the tree
     */
    public Position<V> getRoot();

    /**
     * Get the left child of a position.
     *
     * @param v
     *     the position to get the child of.
     * @return the child of the position iff hasLeft(v) is true.
     * @throws InvalidPositionException
     *     else
     */
    public Position<V> getLeft(Position<V> v) throws InvalidPositionException;

    /**
     * Get the right child of a position.
     *
     * @param v
     *     the position to get the child of.
     * @return the child of the position iff hasRight(v) is true.
     * @throws InvalidPositionException
     *     else
     */
    public Position<V> getRight(Position<V> v) throws InvalidPositionException;

    /**
     * Check if a position has a left child.
     *
     * @param v
     *     position to check.
     * @return true iff v has a left child.
     * @throws InvalidPositionException
     *     if v is not valid (e.g. null)
     */
    public boolean hasLeft(Position<V> v) throws InvalidPositionException;

    /**
     * Check if a position has a right child.
     *
     * @param v
     *     position to check.
     * @return true iff v has a right child.
     * @throws InvalidPositionException
     *     if v is not valid (e.g. null)
     */
    public boolean hasRight(Position<V> v) throws InvalidPositionException;

    /**
     * Adds a new entry to the tree.
     *
     * @param key
     *     to use.
     * @param value
     *     to add.
     */
    public void add(int key, V value);
}

class BTNode<V> implements Position<V> {
    private int key;
    private V value;
    private Position<V> parent, left, right;

    /**
     * Simple constructor.
     *
     * @param key
     *     to set as key.
     * @param value
     *     to set as value.
     */
    public BTNode(int key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    /**
     * @param value
     *     to set.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * @return the parent.
     */
    public Position<V> getParent() {
        return parent;
    }

    /**
     * @return the left child.
     */
    public Position<V> getLeft() {
        return left;
    }

    /**
     * @return the right child.
     */
    public Position<V> getRight() {
        return right;
    }

    /**
     * @param parent
     *     to set
     */
    public void setParent(Position<V> parent) {
        this.parent = parent;
    }

    /**
     * @param left
     *     to set
     */
    public void setLeft(Position<V> left) {
        this.left = left;
    }

    /**
     * @param right
     *     to set
     */
    public void setRight(Position<V> right) {
        this.right = right;
    }
}

class BinaryTree<V> implements BTree<V> {
    private Position<V> root;

    /**
     * Simple constructor.
     */
    public BinaryTree() {
        this.root = null;
    }

    @Override
    public Position<V> getRoot() {
        return root;
    }

    @Override
    public Position<V> getLeft(Position<V> v) throws InvalidPositionException {
        BTNode<V> node = checkPosition(v);
        return node.getLeft();
    }

    @Override
    public Position<V> getRight(Position<V> v) throws InvalidPositionException {
        BTNode<V> node = checkPosition(v);
        return node.getRight();
    }

    @Override
    public boolean hasLeft(Position<V> v) throws InvalidPositionException {
        BTNode<V> node = checkPosition(v);
        return node.getLeft() != null;
    }

    @Override
    public boolean hasRight(Position<V> v) throws InvalidPositionException {
        BTNode<V> node = checkPosition(v);
        return node.getRight() != null;
    }

    @Override
    public void add(int key, V value) {
        if (root == null) {
            root = new BTNode<>(key, value);
        } else {
            this.add((BTNode<V>) root, new BTNode<>(key, value));
        }
    }

    /**
     * Adds the newNode recursively at the right position.
     *
     * @param node
     *     currently examining this node to see if the newnode goes here
     * @param newNode
     *     to place somewhere.
     */
    private void add(BTNode<V> node, BTNode<V> newNode) {
        if (node.getKey() == newNode.getKey()) {
            node.setValue(newNode.getValue());
        } else {
            if (node.getKey() > newNode.getKey()) {
                if (node.getLeft() == null) {
                    newNode.setParent(node);
                    node.setLeft(newNode);
                } else {
                    this.add((BTNode<V>) node.getLeft(), newNode);
                }
            } else {
                if (node.getRight() == null) {
                    newNode.setParent(node);
                    node.setRight(newNode);
                } else {
                    this.add((BTNode<V>) node.getRight(), newNode);
                }
            }
        }
    }

    /**
     * Checks if the position is valid
     *
     * @param v
     *     position to check
     * @return v iff v instanceof BTNode.
     * @throws InvalidPositionException
     *     else
     */
    private BTNode<V> checkPosition(Position<V> v) throws InvalidPositionException {
        if (!(v instanceof BTNode)) {
            throw new InvalidPositionException("Position was null");
        }
        BTNode<V> result = (BTNode<V>) v;
        return result;
    }
}

class InvalidPositionException extends Exception {
    private static final long serialVersionUID = -8010862139713793775L;

    public InvalidPositionException(String string) {
        super(string);
    }
}