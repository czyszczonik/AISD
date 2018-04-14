package Lista3;

import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertTrue;

public class RadixTest {

    private final int setSize = 1000;
    @Test
    public void ascending(){
        RadixSort radixSort = new RadixSort();
        int table[] = generate(setSize);
        radixSort.radixSort(table,true,10);
        for(int element:table){
            System.out.print(element+" ");
        }
        System.out.println();
        assertTrue(sortTest(true,table));
    }

    @Test
    public void desc(){
        RadixSort radixSort = new RadixSort();
        int table[] = generate(setSize);
        radixSort.radixSort(table,false,10);
        for(int element:table){
            System.out.print(element+" ");
        }
        System.out.println();
        assertTrue(sortTest(false,table));
    }


    int[] generate(int size){
        Random random = new Random();

        int[] table = new int[size];
        for(int index = 0;index<size;index++){
            table[index] = random.nextInt(setSize + 1);
        }
        return table;
    }
    boolean sortTest(boolean ascending,int[] table){
        for(int index=0;index<table.length-1;index++){
            if(compare(table[index],table[index+1],ascending)){
                return false;
            }
        }
        return true;
    }
    boolean compare(int element1,int element2,boolean ascending) {
        if(ascending){
            return element1 > element2 ? true : false;
        } else {
            return element1 < element2 ? true : false;

        }
    }
}
