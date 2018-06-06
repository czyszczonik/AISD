package Bloom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    static int elements = 10000; // Number of elements to test


    public static void main(String[] argv) {

        for (int iteration = 1; iteration < 100; iteration++) {
            test(elements,iteration,false);
        }
    }

    private static void test(int elements, int hashes,boolean mode){
        Random r = new Random();
        double fails = 0;
        // Generate elements first
        List<String> existingElements = new ArrayList(elements);
        for (int i = 0; i < elements; i++) {
            byte[] b = new byte[200];
            r.nextBytes(b);
            existingElements.add(new String(b));
        }

        List<String> nonExisting = new ArrayList(elements);
        for (int i = 0; i < elements; i++) {
            byte[] b = new byte[200];
            r.nextBytes(b);
            nonExisting.add(new String(b));
        }
        BloomFilter bloomFilter;
        if(mode) {
            bloomFilter = new BloomFilter(elements, hashes);
        } else {
            bloomFilter = new BloomFilter(elements, hashes,12);
        }
        // Add elements
        for (int i = 0; i < elements; i++) {
            bloomFilter.insert(existingElements.get(i));
        }
        for (int i = 0; i < elements; i++) {
            if(bloomFilter.find(nonExisting.get(i))){
                fails++;
            }
        }
        System.out.println("Liczba Hashy "+bloomFilter.getNumberOfHashes());
        System.out.println("Długość "+bloomFilter.getFilterSize());
        System.out.println("Zapełnienie "+ (double)bloomFilter.getSize()/(double) bloomFilter.getFilterSize()*100);
        System.out.println("Prawdopodobieństwo porażki "+ fails /(double) elements*100);
    }
}
