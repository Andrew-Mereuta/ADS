package KeyBasedSortingHashingMapsSets;

import java.util.*;

/**
 * Entry objects are used to represent "Key-Value" pairs.
 * An entry can be created by using new Entry(String key, Integer Value)
 * The .equals() method of Entry will compare the keys only.
 */
class Entry {
    public final String key;
    public final Integer value;

    public Entry(String s, Integer v) {
        key = s;
        value = v;
    }

    public boolean equals(String s) {
        return s == null && key == null || key.equals(s);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && this.equals(((Entry) o).key);
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}

abstract class HashTable {
    protected LinkedList<Entry>[] myTable;

    /**
     * Constructs a new HashTable.
     *
     * @param capacity
     *     to be used as capacity of the table.
     * @throws IllegalArgumentException
     *     if the input capacity is invalid.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if(capacity<=0){
            throw new IllegalArgumentException();
        }
        myTable = new LinkedList[capacity];
        for(int i=0;i<capacity;i++){
            myTable[i] = new LinkedList<>();
        }

        // TODO
    }

    /**
     * Add a key value pair to the HashTable.
     *
     * @param key
     *     to identify the value.
     * @param value
     *     that is identified by the key.
     */
    public void put(String key, Integer value) {
        //System.out.print(hash(null));
        int index = hash(key);
        index= index%getCapacity();
        if(key==null){
            for(Entry e : myTable[index]){
                if(e.getKey()==null){
                    myTable[index].remove(e);
                }
            }
        }
        for(Entry e : myTable[index]){
            if(e.getKey().equals(key)){
                myTable[index].remove(e);
            }
        }
        myTable[index].add(new Entry(key, value));
        // TODO
    }

    /**
     * @param key
     *     to look for in the HashTable.
     * @return true iff the key is in the HashTable.
     */
    public boolean containsKey(String key) {
        int index = hash(key);
        index= index%getCapacity();
        if(key==null){
            for(Entry e : myTable[index]){
                if(e.getKey()==null){
                    return true;
                }
            }
        }
        for(int i =0; i < myTable[index].size();i++){
            if(myTable[index].get(i).getKey().equals(key)){
                return true;
            }
        }
        return false;
        // TODO
    }

    /**
     * Get a value from the HashTable.
     *
     * @param key
     *     that identifies the value.
     * @return the value associated with the key or `null` if the key is not in the HashTable.
     */
    public Integer get(String key) {
        int index = hash(key);
        index= index%getCapacity();
        if(key==null){
            for(Entry e : myTable[index]){
                if(e.getKey()==null){
                    return e.getValue();
                }
            }
        }
        if(myTable[index].isEmpty()){
            return null;
        }
        else{
            for(int i =0; i < myTable[index].size();i++){
                if(myTable[index].get(i).getKey().equals(key)){
                    return myTable[index].get(i).getValue();
                }
            }
        }
        return null;
        // TODO
    }

    /**
     * @return the capacity of the HashTable.
     */
    public int getCapacity() {
        return myTable.length;
    }

    /**
     * Hashes a string/key.
     *
     * @param item
     *     to hash.
     * @return the hashcode of the string, modulo the capacity of the HashTable.
     */
    public abstract int hash(String item);
}