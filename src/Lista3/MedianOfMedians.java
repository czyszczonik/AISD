package Lista3;

import java.util.Arrays;

public class MedianOfMedians {
    public int select(int[] table,int k){
        return select(table,k,0,table.length-1);
    }
    private int select(int[] table, int k, int leftIndex, int rightIndex){
        if(leftIndex == rightIndex){
            return table[leftIndex];
        }
        int pivot = getPivot(table,leftIndex,rightIndex);
        int largerst = partition(table,leftIndex,rightIndex,pivot);
        int length = largerst - leftIndex + 1;

        if(length == k){
            return table[largerst];
        }
        if(length > k){
            return select(table,k,leftIndex,largerst-1);
        } else {
            return select(table,k-length,largerst+1,rightIndex);
        }
    }

    private int getPivot(int[] table, int leftIndex, int rightIndex){
        int length = leftIndex - rightIndex + 1;
        if(length <= 5){
            Arrays.sort(table);
            return table[table.length/2];
        }
        int[] medians = new int[(int)Math.ceil((double)length/5)];
        int medianIndex = 0;
        int temp[];
        while(leftIndex <= rightIndex){
            temp = new int[Math.min(5,length)];
            for(int index=0;index<temp.length && leftIndex <= rightIndex;index++)
            {
                temp[index] = table[leftIndex];
                leftIndex++;
            }
            Arrays.sort(temp);

            medians[medianIndex] = temp[temp.length/2];
            medianIndex++;
        }
        return getPivot(medians,0,medians.length-1);
    }

    private int partition(int[] table,int low, int high,int pivot){
        while(low < high){
            while(table[low] < pivot){
                low ++;
            }
            while(table[high] > pivot){
                high--;
            }
            if(table[low] == table[high]){
                low ++;
            } else if(low < high){
                int temp = table[low];
                table[low] = table[high];
                table[high] = temp;
            }
        }
        return high;
    }
}
