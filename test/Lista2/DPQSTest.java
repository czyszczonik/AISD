package Lista2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DPQSTest {
    DPQS dpqs = new DPQS();

    @Test
    public void ascendingTest(){
        int[] table = {21,32,1,6,712,5555,23,13};
        int[] compare = table;
        table = dpqs.sort(table,true);
        for(int element:table){
            System.out.print(element+" ");
        }
        assertEquals(1,table[0]);
        assertEquals(6,table[1]);

    }

    @Test
    public void descendingSortTest(){
        int[] table = {21,32,1,2,5,6,712,5555,23,13};
        table = dpqs.sort(table,false);
        for(int element:table){
            System.out.print(element+" ");
        }
        assertEquals(5555,table[0]);
        assertEquals(712,table[1]);
    }

}
