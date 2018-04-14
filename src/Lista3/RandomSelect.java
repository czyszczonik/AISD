package Lista3;

public class RandomSelect {

    int swap = 0;
    int comp = 0;

    public int select(int[] table, int position){
        comp =0;
        swap=0;
        int rand = randomSelect(table,0,table.length-1,position);
        System.err.println("Swaps: "+swap+ " Comps:"+comp);
        return rand;
    }

    private int randomSelect(int[] table, int lowerBound, int higherBound, int position){
        System.err.println("randomSelect");
        for(int iterator = lowerBound;iterator<higherBound;iterator++){
            System.err.print(table[iterator]+" ");
        }
        System.err.println();
        System.err.println("Position" +position);

        if(higherBound==lowerBound){
            return table[lowerBound];
        }
        int pivot = randomisedPartition(table,lowerBound,higherBound);
        System.err.println("Pivot: " +pivot);
        int size = pivot - lowerBound + 1;
        if(position == size){
            return table[pivot];
        } else if(position < size){
            return randomSelect(table,lowerBound,pivot-1,position);
        } else {
            return randomSelect(table,pivot+1,higherBound,position-size);
        }
    }

    private int randomisedPartition(int[] table,int lowerBound, int higherBound){
        System.err.println("randomisedPartition");
       int randomIndex=lowerBound + (int)(Math.random() * ((higherBound - lowerBound) + 1));
       swap(table,higherBound,randomIndex);
       return partition(table,lowerBound,higherBound);
    }
    private int partition(int[] table,int lowerBound, int higherBound){

        int pivot = table[higherBound];
        System.err.println("Pivot:" +pivot);
        int iterator = lowerBound-1;
        for(int index = lowerBound;index<higherBound;index++){
            if(table[index]<=pivot){
                System.err.println("Compare table[index]="+table[index] +"<= pivot ="+pivot);
                comp++;
                iterator++;
                swap(table,iterator,index);
            }
        }
        swap(table,iterator+1,higherBound);
        return iterator+1;
    }
    private void swap(int[] table, int index1, int index2){
        swap++;
        System.err.println("Swap "+table[index1]+" and "+ table[index2]);
        int temporary = table[index1];
        table[index1]=table[index2];
        table[index2]=temporary;
    }
}
