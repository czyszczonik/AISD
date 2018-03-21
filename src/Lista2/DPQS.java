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

            int newLeft = left + 1;
            int newRight = right - 1;
            int iterator = newLeft;
            int side = 0;

            while (iterator <= newRight) {
                if (side >= 0) {
                    if (!compare(table[iterator],pivotRight,ascending)) {
                        swap(table, newLeft, iterator);
                        newLeft++;
                        iterator++;
                        side++;
                    } else {
                        if (!compare(table[iterator],pivotLeft,ascending)) {
                            iterator++;
                        } else {
                            swap(table, iterator, newRight);
                            newRight--;
                            side--;
                        }
                    }
                } else {
                    if (compare(table[newRight],pivotLeft,ascending)) {
                        newRight--;
                        side--;
                    } else {
                        if (!compare(table[newRight],right,ascending)) {
                            rotate(table, newRight, iterator, newLeft);
                            newLeft++;
                            side++;
                        } else {
                            swap(table, iterator, newRight);
                        }
                        iterator++;
                    }
                }
            }
            table[left] = table[newLeft - 1];
            table[newLeft - 1] = pivotRight;
            table[right] = table[newRight + 1];
            table[newRight + 1] = pivotLeft;
            dpqs(table, ascending, left, newLeft - 2);
            dpqs(table, ascending, newLeft, newRight);
            dpqs(table, ascending, newRight + 2, right);
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
