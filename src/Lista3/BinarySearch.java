package Lista3;

public class BinarySearch {

    public BinarySearch() {
        this.comp = 0;
    }

    private int comp;

    public int search(int[] table,int value){
        comp = 0;
        return binarySearch(table,0,table.length-1,value);
    }
    private int binarySearch(int[] table, int lowerBound, int higherBound, int value){
        if (higherBound>=lowerBound){
            int mid = lowerBound + (higherBound - lowerBound)/2;
            comp++;
            if (table[mid] == value){
                return mid;
            }
            comp++;
            if (table[mid] > value) {
                return binarySearch(table, lowerBound, mid - 1, value);
            }
            return binarySearch(table, mid+1, higherBound, value);
        }
        return -1;
    }

    public int getComp() {
        return comp;
    }
}
