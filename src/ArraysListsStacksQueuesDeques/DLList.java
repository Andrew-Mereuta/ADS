package ArraysListsStacksQueuesDeques;

class DLList {
    class Node {
        // Each node object has these three fields
        private Object element;
        private Node previous;
        private Node next;

        // Constructor: Creates a Node object with element = e, previous = p and next = n
        Node(Object e, Node p, Node n) {
            element = e;
            previous = p;
            next = n;
        }

        // This function gets Object e as input and sets e as the element of the Node
        public void setElement(Object e) {
            element = e;
        }

        // This function returns the element variable of the Node
        public Object getElement() {
            return element;
        }

        // This function gets Node n as input and sets the next variable of the current Node object as n.
        public void setNext(Node n) {
            next = n;
        }

        // This function returns the next Node
        public Node getNext() {
            return next;
        }

        // This function gets Node p as input and sets the previous variable of the current Node object as p.
        public void setPrevious(Node p) {
            previous = p;
        }

        // This function returns the previous Node
        public Node getPrevious() {
            return previous;
        }
    }

    // Each object in DLList has one field head, which points to the starting Node of DLList.
    private Node head;
    // Each object in DLList has one field tail, which points to the final Node of DLList.
    private Node tail;

    /**
     * Constructor: initialises the head and tail fields as null
     */
    public DLList() {
        head = null;
        tail = null;
    }

    /**
     * @return The element in the head Node of the DLL
     */
    public Object getHead() {
        return head.getElement();
    }

    /**
     * @return The element in the tail Node of the DLL
     */
    public Object getTail() {
        return tail.getElement();
    }

    /**
     * Adds element e in a new Node to the head of the list.
     *
     * @param e
     *     The element to add.
     */

    public void addFirst(Object e) {
        if(head == null){
            Node node = new Node(e, null, null);
            head = node;
            tail = node;
        }
        else{
            Node node = new Node(e, null, head);
            head.setPrevious(node);
            head = node;
        }
        // TODO
    }

    /**
     * Remove the first Node in the list and return its element.
     *
     * @return The element of the head Node. If the list is empty, this method returns null.
     */
    public Object removeFirst() {
        if (head == null) {
            return null;
        }
        else{
            Node node = head;
            head = head.getNext();
            if(head!=null) {
                head.setPrevious(null);
            }
            else{
                head=null;//!!!!!!!!!!!!!!!
                tail=null;
            }
            return node.getElement();
        }
        // TODO
    }

    /**
     * Adds element e in a new Node to the tail of the list.
     *
     * @param e
     *     The element to add.
     */
    public void addLast(Object e) {
        if(tail==null){
            addFirst(e);
        }
        else{
            Node node = new Node(e, tail, null);
            tail.setNext(node);
            tail = node;
        }
        // TODO
    }

    /**
     * Remove the last Node in the list and return its element.
     *
     * @return The element of the tail Node. If the list is empty, this method returns null.
     */
    public Object removeLast() {
        if(tail==null) {
            return null;
        }
        else{
            Node node = tail;
            if(tail.getPrevious()!=null) {
                tail = tail.getPrevious();
                tail.setNext(null);
            }
            else{
                tail=null;
                head=null;
            }
            return node.getElement();

        }
        // TODO
    }

    /**
     * @return the number of Nodes in the list
     */
    public int size() {
        if(head==null && tail==null){
            return 0;
        }
        int count = 1;
        Node current = head;
        while(current.getNext()!=null){
            count++;
            current = current.getNext();
        }
        return count;
        // TODO
    }


    /**
     * Adds element e in a new Node which is inserted at position pos.
     * The list is zero indexed, so the first element in the list corresponds to position 0.
     * This also means that `addAtPosition(0, e)` has the same effect as `addFirst(e)`.
     * If there is no Node in position pos, this method adds it to the last position.
     *
     * @param pos
     *     The position to insert the element at.
     * @param e
     *     The element to add.
     */
    public void addAtPosition(int pos, Object e) {
        if(pos<0 || pos >=this.size()){
            addLast(e);
        }
        else if(pos==0){
            addFirst(e);
        }
        else{
            Node current =head;
            int i=0;
            while(i!=pos){
                current = current.getNext();
                i++;
            }
            Node node = new Node(e, current.getPrevious(), current);
            current.getPrevious().setNext(node);
            current.setPrevious(node);
        }
        // if(pos==0){
        //     addFirst(e);
        // }
        // else if(pos>=this.size() || pos<0){
        //     addLast(e);
        // }
        // else{
        //     int i=1;
        //     Node current = head.getNext();
        //     while(i!=pos){
        //         i++;
        //         current = current.getNext();
        //     }
        //     Node node = new Node(e, current.getPrevious(), current);
        //     current.getPrevious().setNext(node);
        //     current.setPrevious(node);
        // }

        // TODO
    }

    /**
     * Remove Node at position pos and return its element.
     * The list is zero indexed, so the first element in the list corresponds to position 0.
     * This also means that `removeFromPosition(0)` has the same effect as `removeFirst()`.
     *
     * @param pos
     *     The position to remove the Node from.
     * @return The element of the Node in position pos. If there is no Node in position pos, this method returns null.
     */
    public Object removeFromPosition(int pos) {
        if(pos < 0 || pos >= this.size()){
            return null;
        }
        else if(pos==0){
            return removeFirst();
        }
        else if(pos==(this.size()-1)){
            return removeLast();
        }
        else{
            int i=0;
            Node current = head;
            while(i!=pos){
                current = current.getNext();
                i++;
            }
            Object tmp =current.getElement();
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            current.setPrevious(null);
            current.setNext(null);
            return tmp;
        }
        // TODO
    }
    /**
     * @return A new DLL that contains the elements of the current one in reversed order.
     */
    public DLList reverse() {
        if(head==null && tail==null)
            return null;
        else{
            DLList list = new DLList();
            int i=0;
            while(i<this.size()){
                i++;
                Object node = this.removeLast();
                this.addFirst(node);
                list.addLast(node);
            }
            return list;
        }
        // TODO
    }
}
