package Lista2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class quickSortTest {

    quickSort quickSort = new quickSort();

    @Test
    public void ascendingTest(){
        int[] table = {21,32,1,2,13,6,712,5555,23};
        int[] compare = table;
        table = quickSort.sort(table,true);
        assertEquals(1,table[0]);
        assertEquals(2,table[1]);
    }

    @Test
    public void descendingSortTest(){
        int[] table = {21,32,1,2,5,6,712,5555,23,13};
        table = quickSort.sort(table,false);
        assertEquals(5555,table[0]);
        assertEquals(712,table[1]);
    }

}
