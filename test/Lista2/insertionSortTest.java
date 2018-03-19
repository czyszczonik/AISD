package Lista2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class insertionSortTest {

    insertionSort insertionSort = new insertionSort();

    @Test
    public void ascendingTest(){
        int[] table = {21,32,1,2,5,6,712,5555,23,13};
        int[] compare = table;
        table = insertionSort.sort(table,true);
        assertEquals(1,table[0]);
        assertEquals(2,table[1]);
    }

    @Test
    public void descendingSortTest(){
        int[] table = {21,32,1,2,5,6,712,5555,23,13};
        table = insertionSort.sort(table,false);
        assertEquals(5555,table[0]);
        assertEquals(712,table[1]);
    }
}
