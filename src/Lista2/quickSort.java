package Lista2;

public class quickSort {
    private int comp;
    private int swap;

    public int[] sort(int[] table, boolean ascending) {
        comp = 0;
        swap = 0;
        return quickSort(table,ascending,0,table.length-1);
    }

    private int[] quickSort(int[] table, boolean ascending, int left, int right){
        if(left<right){
            int pivot = partition(table,ascending,left,right);
            quickSort(table,ascending,left,pivot-1);
            quickSort(table,ascending,pivot+1,right);
        }
        return table;
    }

    private int partition(int[] table, boolean ascending, int left, int right){
        int pivot = pickPivot(left,right);
        int pivotElement = table[pivot];
        //nasz pivot leci na prawo
        swap(table,pivot,right);
        int actual = left;
        //przechodzimy od lewa do prawa
        for(int index = left; index < right;index++){
            //w zależności od porównania przerzucamy element
            if(compare(pivotElement,table[index],ascending)){
                swap(table,index,actual);
                actual++;
            }
        }
        swap(table,actual,right);
        return actual;
    }

    public int pickPivot(int left,int right){
        return (left + right) / 2;
    }

    private void swap(int[] table, int index1, int index2){
        swap+=2;
        int temporary = table[index1];
        table[index1] = table[index2];
        table[index2] = temporary;

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
