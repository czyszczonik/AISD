package Lista3;

import java.util.Random;
import java.util.Scanner;

public class zad2 {

    public static void main(String args[]){
        zad2(args);
    }

    static void zad2(String args[]){
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int position = scan.nextInt();
        scan.close();
        if(args[0].equals("-r")){
            int[] table = generateRandom(size);
            int[] second = table.clone();
            RandomSelect randomSelect = new RandomSelect();
            int index = randomSelect.select(table,position);
            printArray(table,index);
            MedianOfMedians medianOfMedians = new MedianOfMedians();
            int index2 = medianOfMedians.select(table,position);
            System.err.println("Compare= "+medianOfMedians.comparisons);
            printArray(second,index2);
        } else if(args[0].equals("-p")){
            int[] table = getPermutation(size);
            RandomSelect randomSelect = new RandomSelect();
            int index = randomSelect.select(table,position);
            printArray(table,index);
            table = getPermutation(size);
            MedianOfMedians medianOfMedians = new MedianOfMedians();
            index = medianOfMedians.select(table,position);
            printArray(table,index);
        }
    }
    static int[] getPermutation(int size) {
        // Creating a object for RandomPath class
        int[] arr = new int[size];
        for (int index = 0; index < size; index++) {
            arr[index]=index+1;
        }
        Random r = new Random();

        for (int i = size - 1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i);

            // Swap arr[i] with the HashNode at random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
            return arr;
    }


    static int[] generateRandom(int size) {
        Random random = new Random();

        int[] table = new int[size];
        for (int index = 0; index < size; index++) {
            table[index] = random.nextInt(100000 + 1);
        }
        return table;
    }

    static void printArray(int[] array, int value){
        for (int index = 0;index<array.length;index++){
            if(array[index] == value){
                System.out.print("["+array[index]+"] ");
            } else {
                System.out.print(array[index]+" ");
            }
        }
        System.out.println();
    }
}
