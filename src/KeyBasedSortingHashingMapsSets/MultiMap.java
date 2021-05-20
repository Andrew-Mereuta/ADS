package KeyBasedSortingHashingMapsSets;

import java.util.*;

public class MultiMap {
    private Map<Integer, List<Integer>> map;

    /**
     * Creates a new MultiMap.
     */
    public MultiMap() {
        map = new HashMap<>();
        // TODO
    }

    /**
     * @return The number of (key, value) pairs in the MultiMap.
     */
    public int size() {
        int size = 0;
        Iterator<Integer> it = map.keySet().iterator();
        while(it.hasNext()){
            size += map.get(it.next()).size();
        }
        return size;
        // TODO
    }

    /**
     * @return True if the MultiMap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
        // TODO
    }

    /**
     * Adds the given (key, value) pair to the MultiMap.
     *
     * @param key Key for the new item.
     * @param value New item to add to the MultiMap.
     */
    public void put(int key, int value) {
        if(map.get(key) == null){
            List<Integer> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        } else {
            List<Integer> list = map.get(key);
            list.add(value);
            map.replace(key, list);
        }
        // TODO
    }

    /**
     * Returns all values in the MultiMap for the given key.
     *
     * @param key Key to return all entries for.
     * @return A list of all entries for the given key.
     *         If the key is not in the map, return an empty list.
     */
    public List<Integer> get(int key) {
        if(map.containsKey(key)){
            return map.get(key);
        } else {
            return new ArrayList<>();
        }
        // TODO
    }

    /**
     * Removes the given (key, value) pair from the MultiMap.
     *
     * @param key Key for the value that should be removed.
     * @param value Value to remove.
     * @return True if removal was successful, false otherwise.
     */
    public boolean remove(int key, int value) {
        if(map.containsKey(key)){
            List<Integer> list = map.get(key);
            if(list.contains(value)){
                for(int i = 0; i<list.size(); i++){
                    if(list.get(i) == value){
                        list.remove(i);
                        map.replace(key, list);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
        // TODO
    }
}
