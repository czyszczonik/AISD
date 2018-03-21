package Lista2;

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

public class zad1 {
    public static void main(String args[]){
        if(args.length == 4){
            zad1(args);
        } else if(args.length == 7){
            zad2(args);
        } else {
            System.err.println("Wrong Usage!");
        }
    }

    static void zad1(String args[]){
        if(!checkType(args)){
            return;
        }
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        String input = scan.nextLine();
        scan.close();

        int[] table = Arrays.stream(input.substring(0,input.length()).split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean ascending;
        switch (args[3]) {
            case "'>='":
                ascending = true;
                break;
            case "'<='":
                ascending = false;
                break;
            default:
                System.err.print("Wrong comparison type!");
                return;
        }
        long startTime=0;
        long endTime=0;
        switch (args[1]) {
            case "insert":
                insertionSort insertionSort = new insertionSort();
                startTime = System.nanoTime();
                insertionSort.sort(table, ascending);
                endTime = System.nanoTime();
                System.err.println("Comparisons= "+insertionSort.getComp());
                System.err.println("Moves= "+insertionSort.getSwap());
                break;
            case "merge":
                mergeSort mergeSort = new mergeSort();
                startTime = System.nanoTime();
                mergeSort.sort(table, ascending);
                endTime = System.nanoTime();
                System.err.println("Comparisons= "+mergeSort.getComp());
                System.err.println("Moves= "+mergeSort.getSwap());
                break;
            case "DPQS":
                DPQS dpqs = new DPQS();
                startTime = System.nanoTime();
                dpqs.sort(table, ascending);
                endTime = System.nanoTime();
                System.err.println("Comparisons= "+dpqs.getComp());
                System.err.println("Moves= "+dpqs.getSwap());
                break;
            case "quick":
                quickSort quickSort = new quickSort();
                startTime = System.nanoTime();
                quickSort.sort(table, ascending);
                endTime = System.nanoTime();
                System.err.println("Comparisons= "+quickSort.getComp());
                System.err.println("Moves= "+quickSort.getSwap());
                break;
        }
        long duration = (endTime - startTime)/1000000;
        System.err.println("Time: "+duration+" miliseconds.");
    }

    static void zad2(String args[]){
        if(!checkType(args)){
            return;
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(args[5]+".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace(); }
        writer.println("iterator;comp;swap;duration");
        writer.close();

        long startTime=0;
        long endTime=0;
        int comp = 0;
        int swap = 0;
        boolean ascending = false;
        int repeats = Integer.parseInt(args[6]);


        for(int iterator = 100;iterator<=10000;iterator+=100){
            for(int repeat = 0;repeat < repeats;repeat++){
                int table[] = generate(iterator);
                switch (args[3]) {
                    case "'<='":
                        ascending = false;
                        break;
                    case "'>='":
                        ascending = true;
                        break;
                    default:
                        System.err.print("Wrong comparison type!");
                        return;
                } // END args[3] switch case
                switch (args[1]) {
                    case "DPQS":
                        DPQS dpqs = new DPQS();
                        startTime = System.nanoTime();
                        dpqs.sort(table, ascending);
                        endTime = System.nanoTime();
                        comp = dpqs.getComp();
                        swap = dpqs.getSwap();
                        break;
                    case "merge":
                        mergeSort mergeSort = new mergeSort();
                        startTime = System.nanoTime();
                        mergeSort.sort(table, ascending);
                        endTime = System.nanoTime();
                        comp = mergeSort.getComp();
                        swap = mergeSort.getSwap();
                        break;
                    case "quick":
                        quickSort quickSort = new quickSort();
                        startTime = System.nanoTime();
                        quickSort.sort(table, ascending);
                        endTime = System.nanoTime();
                        comp = quickSort.getComp();
                        swap = quickSort.getSwap();
                        break;
                    case "insert":
                        insertionSort insertionSort = new insertionSort();
                        startTime = System.nanoTime();
                        insertionSort.sort(table, ascending);
                        endTime = System.nanoTime();
                        comp = insertionSort.getComp();
                        swap = insertionSort.getSwap();
                        break;

                }//END Args[1] switch case

                long duration = (endTime - startTime) / 1000;

                String output = iterator+";"+comp+";"+swap+";"+duration+"\n";
                try {
                    Files.write(Paths.get(args[5]+".txt"), output.getBytes(), StandardOpenOption.APPEND);
                }catch (IOException e) {
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

    static boolean checkType(String args[]){
        String[] types = {"insert", "quick", "merge","DPQS"};
        if(Arrays.stream(types).anyMatch(element -> element.equals(args[1]))){
            return true;
        } else {
            return false;
        }
    }

    static int[] generate(int size){
        Random random = new Random();

        int[] table = new int[size];
        for(int index = 0;index<size;index++){
            table[index] = random.nextInt(100000 + 1);
        }
        return table;
    }

}
