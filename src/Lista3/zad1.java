package Lista3;

import Lista2.DPQS;
import Lista2.insertionSort;
import Lista2.mergeSort;
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
import java.util.concurrent.TimeUnit;

public class zad1 {

    public static void main(String args[]){
        if(args.length != 7){
            System.err.println("Wrong Usage!");
            return;
        }
        zad2(args);
    }
    static void zad2(String args[]) {
        if (!checkType(args)) {
            return;
        }
        int compTypeIndex = 0;
        int fileNameIndex = 0;
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("--comp")) {
                compTypeIndex = index+3 ;
            }
            if (args[index].equals("--type")) {

            }
            if (args[index].equals("--stat")) {
                fileNameIndex = index + 1;
                Integer.parseInt(args[index + 2]);
            }
        }
        String compType = args[compTypeIndex+3];
        boolean ascending;
        if (compType.equals("<=")) {
            ascending = true;
        } else if (compType.equals(">=")) {
            ascending = false;
        } else {
            System.err.print("Wrong comparison type!");
            return;
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(args[fileNameIndex] + ".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println("iterator;comp;swap;duration");
        writer.close();

        long startTime = 0;
        long endTime = 0;
        int comp = 0;
        int swap = 0;
        int repeats = Integer.parseInt(args[6]);

        for (int iterator = 100; iterator <= 10000; iterator += 100) {
            for (int repeat = 0; repeat < repeats; repeat++) {
                int table[] = generate(iterator);
                Runtime runtime = Runtime.getRuntime();
                long memoryBefore= 0;
                long memoryAfter=0;
                switch (args[1]) {
                    case "DPQS":
                        DPQS dpqs = new DPQS();
                        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        startTime = System.nanoTime();
                        dpqs.sort(table, ascending);
                        endTime = System.nanoTime();
                        memoryAfter= runtime.totalMemory() - runtime.freeMemory();
                        comp = dpqs.getComp();
                        swap = dpqs.getSwap();
                        break;
                    case "merge":
                        mergeSort mergeSort = new mergeSort();
                        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        startTime = System.nanoTime();
                        mergeSort.sort(table, ascending);
                        endTime = System.nanoTime();
                        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        comp = mergeSort.getComp();
                        swap = mergeSort.getSwap();
                        break;
                    case "quick":
                        quickSort quickSort = new quickSort();
                        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        startTime = System.nanoTime();
                        quickSort.sort(table, ascending);
                        endTime = System.nanoTime();
                        memoryAfter= runtime.totalMemory() - runtime.freeMemory();
                        comp = quickSort.getComp();
                        swap = quickSort.getSwap();
                        break;
                    case "insert":
                        insertionSort insertionSort = new insertionSort();
                        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        startTime = System.nanoTime();
                        insertionSort.sort(table, ascending);
                        endTime = System.nanoTime();
                        memoryAfter= runtime.totalMemory() - runtime.freeMemory();
                        comp = insertionSort.getComp();
                        swap = insertionSort.getSwap();
                        break;
                    case "radix":
                        RadixSort radixSort = new RadixSort();
                        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        startTime = System.nanoTime();
                        radixSort.radixSort(table, ascending, 10);
                        endTime = System.nanoTime();
                        memoryAfter= runtime.totalMemory() - runtime.freeMemory();
                        comp = radixSort.getComp();
                        swap = radixSort.getSwap();
                        break;

                }//END Args[1] switch case

                long duration = (endTime - startTime) / 1000;
                long mem = memoryAfter - memoryBefore;
                String output = iterator + ";" + comp + ";" + swap + ";" + duration + ";"+ +mem+"\n";
                try {
                    Files.write(Paths.get(args[5] + ".txt"), output.getBytes(), StandardOpenOption.APPEND);
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
            }//END repeat for loop
        }//END iterator for loop


    }

    static boolean checkType(String args[]) {
        String[] types = {"insert", "quick", "merge", "DPQS","radix"};
        if (Arrays.stream(types).anyMatch(element -> element.equals(args[1]))) {
            return true;
        } else {
            return false;
        }
    }

    static int[] generate(int size) {
        Random random = new Random();

        int[] table = new int[size];
        for (int index = 0; index < size; index++) {
            table[index] = random.nextInt(100000 + 1);
        }
        return table;
    }

    static boolean sortTest(boolean ascending, int[] table) {
        for (int index = 0; index < table.length - 1; index++) {
            if (compare(table[index], table[index + 1], ascending)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare(int element1, int element2, boolean ascending) {
        if (ascending) {
            return element1 > element2 ? true : false;
        } else {
            return element1 < element2 ? true : false;

        }
    }
}


