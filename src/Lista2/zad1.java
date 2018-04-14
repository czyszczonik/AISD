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
        int compTypeIndex = 0;
        int sortTypeIndex = 0;
        for (int index = 0; index<args.length;index++) {
            if(args[index].equals("--type")){
                sortTypeIndex=index+1;
            }
            if(args[index].equals("--comp")){
                compTypeIndex=index+1;
            }
        }
        String compType = args[compTypeIndex];
        boolean ascending;
        if(compType.equals(">=")){
            ascending = true;
        } else if(compType.equals("<=")){
            ascending = false;
        } else {
            System.err.print("Wrong comparison type!");
            return;
        }

        Scanner scan = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        int size = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < size; i++) {
            input.append(scan.nextInt()+" ");
        }
        input.toString();
        scan.close();

        int[] table = Arrays.stream(input.substring(0,input.length()).split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        long startTime=0;
        long endTime=0;
        switch (args[sortTypeIndex]) {
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
        if(sortTest(ascending,table)){
            System.err.println("OK");
        } else {
            System.err.println("Wrong");
        }
        System.out.println(table.length);
        String output = "";
        for (int number:table) {
            System.out.print(number+" ");

        }
        System.out.println("");
    }

    static void zad2(String args[]){
        if(!checkType(args)){
            return;
        }
        int compTypeIndex = 0;
        int fileNameIndex = 0;
        for (int index = 0; index<args.length;index++) {
            if(args[index].equals("--type")){
            }
            if(args[index].equals("--comp")){
                compTypeIndex=index+1;
            }
            if(args[index].equals("--stat")){
                fileNameIndex = index+1;
                Integer.parseInt(args[index + 2]);
            }
        }
        String compType = args[compTypeIndex];
        boolean ascending;
        if(compType == ">="){
            ascending = true;
        } else if(compType == "<="){
            ascending = false;
        } else {
            System.err.print("Wrong comparison type!");
            return;
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(args[fileNameIndex]+".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace(); }
        writer.println("iterator;comp;swap;duration");
        writer.close();

        long startTime=0;
        long endTime=0;
        int comp = 0;
        int swap = 0;
        int repeats = Integer.parseInt(args[6]);


        for(int iterator = 100;iterator<=10000;iterator+=100){
            for(int repeat = 0;repeat < repeats;repeat++){
                int table[] = generate(iterator);
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
    static boolean sortTest(boolean ascending,int[] table){
        for(int index=0;index<table.length-1;index++){
            if(compare(table[index],table[index+1],ascending)){
                return false;
            }
        }
        return true;
    }

    private static boolean compare(int element1,int element2,boolean ascending) {
        if(ascending){
            return element1 > element2 ? true : false;
        } else {
            return element1 < element2 ? true : false;

        }
    }

}
