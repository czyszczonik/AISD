package Lista2;

class DPQS {
    private int comp;
    private int swap;
    public DPQS(){}

    public int[] sort(int[] table, boolean ascending) {
        comp = 0;
        swap = 0;
        return dpqs(table,ascending,0,table.length-1);
    }
    private int[] dpqs(int[] table,boolean ascending,int left,int right){
        if(right > left) {
            int pivotRight;
            int pivotLeft;
            if (!compare(table[right],table[left],ascending)) {
                pivotRight = table[right];
                pivotLeft = table[left];
            } else {
                pivotRight = table[left];
                pivotLeft = table[right];
            }

            int i = left + 1;
            int k = right - 1;
            int j = i;
            int d = 0;

            while (j <= k) {
                if (d >= 0) {
                    if (!compare(table[j],pivotRight,ascending)) {
                        swap(table, i, j);
                        i++;
                        j++;
                        d++;
                    } else {
                        if (!compare(table[j],pivotLeft,ascending)) {
                            j++;
                        } else {
                            swap(table, j, k);
                            k--;
                            d--;
                        }
                    }
                } else {
                    if (compare(table[k],pivotLeft,ascending)) {
                        k--;
                        d--;
                    } else {
                        if (!compare(table[k],right,ascending)) {
                            rotate(table, k, j, i);
                            i++;
                            d++;
                        } else {
                            swap(table, j, k);
                        }
                        j++;

                    }
                }
            }
            table[left] = table[i - 1];
            table[i - 1] = pivotRight;
            table[right] = table[k + 1];
            table[k + 1] = pivotLeft;
            swap+=2;
            dpqs(table, ascending, left, i - 2);
            dpqs(table, ascending, i, k);
            dpqs(table, ascending, k + 2, right);
        } return table;
    }

    private boolean compare(int element1,int element2,boolean ascending) {
        comp++;
        if(ascending){
            return element1 > element2 ? true : false;
        } else {
            return element1 < element2 ? true : false;

        }
    }

    private void swap(int[] table, int index1, int index2){
        swap+=2;
        int temporary = table[index1];
        table[index1] = table[index2];
        table[index2] = temporary;

    }

    private void rotate(int[] table, int index1, int index2, int index3){
        int temporary = table[index1];
        table[index1] = table[index2];
        table[index2] = table[index3];
        table[index3] = temporary;
        swap+=3;
    }

    public int getComp() {
        return comp;
    }

    public int getSwap() {
        return swap;
    }
}
