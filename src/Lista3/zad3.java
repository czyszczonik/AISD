package Lista3;

import Lista2.quickSort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class zad3 {

    public static void main(String args[]){
        zad3();
    }

    static void zad3() {
        Random random = new Random();
        quickSort quickSort = new quickSort();
        long startTime, endTime;
        Scanner scan = new Scanner(System.in);
        BinarySearch binarySearch = new BinarySearch();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("BinarySearch" + ".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println("iterator;comp;duration");
        writer.close();
        for (int iteration = 1000; iteration <= 100000; iteration += 1000) {
            int[] table = generate(iteration);
            table = quickSort.sort(table,true);
            int value = table[1];
//        StringBuilder input = new StringBuilder();
//        for (int i = 0; i < iteration; i++) {
//            input.append(scan.nextInt() + " ");
//        }
//        input.toString();
//        int value = scan.nextInt();
//        int[] table = Arrays.stream(input.substring(0, input.length()).split(" "))
//                .mapToInt(Integer::parseInt)
//                .toArray();
            startTime = System.nanoTime();
            if(binarySearch.search(table,value) == -1){
            System.out.println("0");
        } else {
            System.out.println("1");
        }
            endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000;
            String output = iteration + ";" + binarySearch.getComp() + ";" + duration + "\n";
            try {
                Files.write(Paths.get("BinarySearch" + ".txt"), output.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            duration = 0;
            startTime = 0;
            endTime = 0;
        }
        scan.close();
    }
    static int[] generate(int size) {
        Random random = new Random();

        int[] table = new int[size];
        for (int index = 0; index < size; index++) {
            table[index] = random.nextInt(100000 + 1);
        }
        return table;
    }
}
