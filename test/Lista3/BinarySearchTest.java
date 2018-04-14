package Lista3;

import Lista2.quickSort;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BinarySearchTest {
    private BinarySearch binarySearch = new BinarySearch();
    private quickSort quickSort = new quickSort();
    private final int setSize = 1000;

    @Test
    public void searchTest(){
        int[] table = generate(1000);
        Random random = new Random();
        int value = table[random.nextInt(setSize)];
        table = quickSort.sort(table,true);
        int index = binarySearch.search(table,value);
        assertEquals(value,table[index]);
    }
    private int[] generate(int size){
        Random random = new Random();

        int[] table = new int[size];
        for(int index = 0;index<size;index++){
            table[index] = random.nextInt(setSize + 1);
        }
        return table;
    }
}
