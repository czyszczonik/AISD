package Lista3;

public class BinarySearch {

    public int search(int[] table,int value){
        return binarySearch(table,0,table.length-1,value);
    }
    private int binarySearch(int[] table, int lowerBound, int higherBound, int value){
        if (higherBound>=lowerBound){
            int mid = lowerBound + (higherBound - lowerBound)/2;
            if (table[mid] == value){
                return mid;
            }
            if (table[mid] > value) {
                return binarySearch(table, lowerBound, mid - 1, value);
            }
            return binarySearch(table, mid+1, higherBound, value);
        }
        return -1;
    }
}
