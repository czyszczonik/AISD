package Lista3;

import Lista2.quickSort;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RandomSelectTest {
    private RandomSelect randomSelect = new RandomSelect();
    private quickSort quickSort = new quickSort();
    private final int setSize = 1000;


    @Test
    public void SelectTest(){
        int[] table = generate(1000);
        Random random = new Random();
        int position = random.nextInt(setSize);
        int selected = randomSelect.select(table,position);
        table = quickSort.sort(table,true);
        assertEquals(selected,table[position-1]);
    }
    int[] generate(int size){
        Random random = new Random();

        int[] table = new int[size];
        for(int index = 0;index<size;index++){
            table[index] = random.nextInt(setSize + 1);
        }
        return table;
    }
}
