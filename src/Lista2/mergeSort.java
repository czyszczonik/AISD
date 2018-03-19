package Lista2;

import java.util.Arrays;

class mergeSort {
    private int comp;
    private int swap;
    public mergeSort(){}

    public int[] sort(int[] table, boolean ascending) {
        comp = 0;
        swap = 0;
        return mergeSort(table,ascending);
    }

    private int[] mergeSort(int[] table,boolean ascending){
        int size = table.length;
        if(size > 1){
            int[] table1 = Arrays.copyOfRange(table, 0, size/2);
            int[] table2 = Arrays.copyOfRange(table, size/2, size);
            return merge(mergeSort(table1, ascending),
                    mergeSort(table2, ascending),
                    ascending);
        } else {
            return table;
        }

    }

    private int[] merge(int[] table1, int[] table2,boolean ascending){
        int[] array = new int[table1.length+table2.length];
        int index1 = 0;
        int index2 = 0;
        int arrayIndex = 0;

        while(table1.length > index1 && table2.length > index2){
            if(compare(table1[index1],table2[index2],ascending)){
                array[arrayIndex] = table2[index2];
                index2++;
                arrayIndex++;
                swap++;
            } else {
                array[arrayIndex] = table1[index1];
                index1++;
                arrayIndex++;
                swap++;
            }
        }

        while(table1.length > index1){
            swap++;
            array[arrayIndex] = table1[index1];
            index1++;
            arrayIndex++;
        }

        while(table2.length > index2){
            array[arrayIndex] = table2[index2];
            index2++;
            arrayIndex++;
            swap++;
        }
        return array;
    }

    private boolean compare(int element1,int element2,boolean ascending) {
        comp++;
        if(ascending){
            return element1 > element2 ? true : false;
        } else {
            return element1 < element2 ? true : false;

        }
    }

    public int getComp() {
        return comp;
    }

    public int getSwap() {
        return swap;
    }
}
