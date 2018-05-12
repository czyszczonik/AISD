package Lista4;

import java.util.ArrayList;

public class HashTable {
    private final int SIZE = 128;
    private HashNode[] table = new HashNode[SIZE];
    ArrayList<String> strings = new ArrayList<>();

    private int getIndex(String key){
        return key.hashCode()%SIZE;
    }

    public void insert(String key){
        table[getIndex(key)].insert(key);
    }

    public int find(String key){
        return table[key.hashCode()%10].find(key);
    }

    public void delete(String key){
        table[key.hashCode()%10].delete(key);
    }


}
