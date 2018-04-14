package Lista3;

public class RadixSort {

    private int comp = 0;
    private int swap = 0;

    public void radixSort(int array[],boolean ascending,int base) {
        comp = 0;
        swap = 0;
        int digitPlace = 1;
        int n = array.length;
        int result[] = new int[n];
        int max = 0;
        base = getBase(array);
        // loop until we reach the largest digit place
        while (digitPlace == 1 || max / digitPlace > 0) {
            int count[] = new int[base];


            // get frequency count of digits at current digit place
            for (int each : array) {

                if (digitPlace == 1) {
                    comp++;
                    if (each > max) {
                        max = each;
                    }
                }

                count[(each / digitPlace) % base]++;
            }

            // counting sort algorithm
            // make each element in the frequency array
            // store the sum of previous counts
            if(ascending) {
                for (int i = 1; i < base; i++) {
                    count[i] += count[i - 1];
                }
            } else {
                for (int i = base-1; i > 0; i--) {
                    count[i-1] += count[i];
                }
            }


            // get index from count array and place original elements
            // into temp array
            for (int i = n - 1; i >= 0; i--) {

                // extract correct digit
                int current = (array[i] / digitPlace) % base;

                result[count[current] - 1] = array[i];
                count[current]--;
            }

            // copy temp array back to original
            System.arraycopy(result, 0, array, 0, n);

            // Move to next digit place
            digitPlace *= base;
        }
    }

    int getBase(int[] table){
        Double base =  (Math.log(getMax(table)) / Math.log(2));
        return base.intValue();
    }

    int getMax(int[] table){
        int max = table[0];
        for (int element:table) {
            if (element>max){
                max = element;
            }
        }
        return max;
    }

    int getComp(){
        return comp;
    }
    int getSwap(){
        return swap;
    }
}
