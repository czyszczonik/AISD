package Lista2;

public class insertionSort {

    private int comp;
    private int swap;
    public insertionSort(){   }

     public int[] sort(int[] table, boolean ascending){
         comp = 0;
         swap = 0;
        return insertionSort(table,ascending);
        }

    private int[] insertionSort(int[] table, boolean ascending){
        int size = table.length;
        for(int index = 1;index < size;++index){

            int key = table[index];
            int iteration = index - 1;

            //Takie dosyć trywalne "przesuwanie" tabeli
            while(iteration >= 0 && compare(table[iteration],key,ascending)){
                table[iteration + 1] = table[iteration];
                swap++;
                iteration--;
            }
            //Zapamiętany int wrzucony na odpowiednią pozycję
            table[iteration + 1] = key;
        }
        return table;
    }

    private boolean compare(int element,int key,boolean ascending) {
        comp++;
        if(ascending){
            return element > key ? true : false;
        } else {
            return element < key ? true : false;

        }
    }

    public int getComp() {
        return comp;
    }

    public int getSwap() {
        return swap;
    }
}
