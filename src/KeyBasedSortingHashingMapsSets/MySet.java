package KeyBasedSortingHashingMapsSets;

import java.util.HashSet;
import java.util.Iterator;

class MySet extends HashSet<String> {
    private static final long serialVersionUID = 1L;

    public MySet() {
        super();
    }

    /**
     * @return the union of the elements of this and that
     */
    public MySet union(MySet that) {
        MySet result = new MySet();
        if(that == null){
            that = new MySet();
        }
        Iterator<String> it = that.iterator();
        while(it.hasNext()){
            result.add(it.next());
        }
        it = this.iterator();
        while(it.hasNext()){
            result.add(it.next());
        }
        // TODO
        return result;
    }

    /**
     * @return the intersection of the elements of this and that
     */
    public MySet intersection(MySet that) {
        if(that == null){
            that = new MySet();
        }
        MySet result = new MySet();
        Iterator<String> it = that.iterator();
        while(it.hasNext()){
            String tmp = it.next();
            if(this.contains(tmp)){
                result.add(tmp);
            }
        }
        // TODO
        return result;
    }

    /**
     * @return the difference of the elements of this and that
     */
    public MySet difference(MySet that) {
        if(that == null){
            that = new MySet();
        }
        MySet result = new MySet();
        Iterator<String> it = this.iterator();
        while(it.hasNext()){
            String tmp = it.next();
            if(!that.contains(tmp)){
                result.add(tmp);
            }
        }
        // TODO
        return result;
    }

    /**
     * @return the exclusive or (XOR) of the elements of this and that
     */
    public MySet exclusiveOr(MySet that) {
        if(that == null){
            that = new MySet();
        }
        MySet result = new MySet();
        Iterator<String> it = that.iterator();
        while(it.hasNext()){
            String tmp = it.next();
            if(!this.contains(tmp)){
                result.add(tmp);
            }
        }
        it = this.iterator();
        while(it.hasNext()){
            String tmp = it.next();
            if(!that.contains(tmp)){
                result.add(tmp);
            }
        }
        // TODO
        return result;
    }

    /**
     * @return a String representation of a MySet object
     */
    public String toString() {
        String res = "<MySet{";
        Iterator<String> it = this.iterator();
        int i=0;
        while(it.hasNext()){
            res += it.next();
            if(i<this.size()-1){
                res+=",";
            }
            i++;
        }
        res+="}>";
        return res;
        // TODO
    }
}