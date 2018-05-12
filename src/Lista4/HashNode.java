package Lista4;

import java.util.Arrays;
import java.util.LinkedList;

public class HashNode {

    private final int transformSize = 150;
    private LinkedList<String> lista = new LinkedList<>();
    private RBTree tree;
    private int size = 0;
    public void insert(String key){
        System.err.println("Insert key" + key);
        if(size>transformSize){
            if(tree == null){
                transformToTree();
            }
            tree.insert(key);
        } else {
            lista.add(key);
        }
        size++;
    }

    public int find(String key){
        System.err.println("Finding key" + key);
        if(lista == null){
            return tree.find(key);
        } else {
            return lista.contains(key) ? 1 : 0;
        }
    }

    public void delete(String key){
        System.err.println("Delete key" + key);

        if(size>transformSize){
            tree.delete(key);
        } else {
            if(lista == null){
                transformToList();
            }
            lista.remove(key);
        }
        size--;
    }

    public void transformToTree(){
        System.err.println("Transform to Tree");
        tree = new RBTree();
        lista.forEach(s -> tree.insert(s));
        lista = null;
    }
    public void transformToList(){
        System.err.println("Transform to List");
        lista = new LinkedList<>();
        String[] values = tree.inOrderSplit().split(";");
        lista.addAll(Arrays.asList(values));
        tree = null;
    }

    public LinkedList<String> getLista() {
        return lista;
    }
}
