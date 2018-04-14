package Lista3;

import java.util.Arrays;
import java.util.Scanner;

public class zad3 {

    public static void main(String args[]){
        zad3();
    }

    static void zad3() {
        Scanner scan = new Scanner(System.in);
        BinarySearch binarySearch = new BinarySearch();
        for (int iteration = 1000; iteration < 10000; iteration *= 2) {
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < iteration; i++) {
            input.append(scan.nextInt() + " ");
        }
        input.toString();
        int value = scan.nextInt();
        int[] table = Arrays.stream(input.substring(0, input.length()).split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        if(binarySearch.search(table,value) == -1){
            System.out.println("0");
        } else {
            System.out.println("1");
        }
    }
        scan.close();
    }
}
