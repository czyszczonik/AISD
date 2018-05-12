package Lista3;

import Lista2.insertionSort;

public class MedianOfMedians {
    public int comparisons = 0;
    insertionSort insertionSort = new insertionSort();
    public int select(int[] array, int position){
        //Sorting and returning array with less than 10 elements.
        for(int iterator = 0;iterator<array.length;iterator++){
            System.err.print(array[iterator]+" ");
        }
        if(array.length < 10){
            System.err.println("Sorting Table");
            array = insertionSort.sort(array,true);
            return array[position-1];
        }
        if(array.length%5!=0){
            System.err.println("Fixing array");
            array = fixArray(array);
        }
        int num_arrays = array.length/5;
        int[][] arrays_of_five = new int[num_arrays][5];
        int[] median_array = new int[num_arrays];
        int[] greater_than_median = new int[array.length];
        int gtm_index = 0; //greater than median index
        int[] less_than_median = new int[array.length];
        int ltm_index = 0; //less than median index
        int etm_index = 0;
        int[] return_array;

        //Partiton to smaller arrays
        System.err.println("Partition");
        for(int i = 0; i < num_arrays; ++i){
            for(int j = 0; j < 5; ++j){
                arrays_of_five[i][j] = array[j+(5 * i)];
            }
        }

        // Computing median of smaller arrays
        System.err.println("Medians of arrays");
        for(int i = 0; i < num_arrays; ++i){
            median_array[i] = select(arrays_of_five[i], 2);
        }

        //median of medians
        int pivot = select(median_array, median_array.length/2);

        for(int i = 0; i < array.length; ++i){
            comparisons+=2;
            if(array[i] > pivot){
                greater_than_median[gtm_index] = array[i];
                ++gtm_index;
                comparisons--;
            }
            else if(array[i] == pivot){
                ++etm_index;
            }
            else{
                less_than_median[ltm_index] = array[i];
                ++ltm_index;
            }
        }

        if(position <= ltm_index){
            System.err.println("Finding median in less than pivot HashNode");
            return_array = new int[ltm_index];
            //HARD COPY !!!
            for(int i = 0; i < ltm_index; ++i){
                return_array[i] = less_than_median[i];
            }
            return select(return_array, position);
        }

        if(position > (ltm_index + etm_index)){
            System.err.println("Finding median in higher than pivot HashNode");

            return_array = new int[gtm_index];
            //HARD COPY !!!
            for(int i = 0; i < gtm_index; ++i){
                return_array[i] = greater_than_median[i];
            }
            return select(return_array, (position - (ltm_index + etm_index)));
        }
        return pivot;
    }

    int[] fixArray(int[] array){
        int[] temp_array = new int[(array.length+5-(array.length%5))];
        int i = array.length;
        for(int a = 0; a < array.length; ++a){
            temp_array[a] = array[a];
        }
        //max value don't break our median ... because it's max ...
        for(int x = 0; x < 5-(array.length%5); ++x){
            temp_array[i] = Integer.MAX_VALUE;
            ++i;
        }
        return temp_array;
    }
}
