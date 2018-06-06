package Bloom;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BloomFilter {

    private boolean[] filter;
    private int filterSize;
    private int numberOfHashes;
    private int size = 0;
    private int elements = 0;

    //Optimal bits per element is -1.44*log2(Probability of Failure)
    private final double bitsPerElement;


    static final Charset charset = Charset.forName("UTF-8"); // encoding used for storing hash values as strings
    static final String hashName = "SHA-512";
    private MessageDigest hashFunction;



    public BloomFilter(int maxSize, int numberOfHashes) {
        try {
            hashFunction = java.security.MessageDigest.getInstance(hashName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        bitsPerElement = getBitsPerElement(0.001);
        this.numberOfHashes = numberOfHashes;
        this.filterSize = (int)Math.ceil(bitsPerElement * maxSize);
        this.filter = getTable(filterSize);
    }

    public BloomFilter(int maxSize, int numberOfHashes,int bitsPerElement) {
        try {
            hashFunction = java.security.MessageDigest.getInstance(hashName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.bitsPerElement = bitsPerElement;
        this.numberOfHashes = numberOfHashes;
        this.filterSize = (int)Math.ceil(bitsPerElement * maxSize);
        this.filter = getTable(filterSize);
    }
    private boolean[] getTable(int size){
        boolean[] table = new boolean[size];
        for (Boolean cell: table) {
            cell = Boolean.FALSE;
        }
        return table;
    }

    public int[] createHashes(byte[] data, int hashes) {
        int[] result = new int[hashes];

        int index = 0;
        byte salt = 0;
        byte[] digest;
        while (index < hashes) {
            salt++;
            hashFunction.update(salt);
            digest = hashFunction.digest(data);
            //NIGDY NIE DAMY DWA RAZY TEJ SAMEJ LICZBY
            for (int i = 0; i < digest.length/4 && index < hashes; i++) {
                int hash = 0;
                //4 bajty - int
                for (int j = (i*4); j < (i*4)+4; j++) {
                    hash <<= 8; //Int ma 8 bitów, ustawiamy wszyskie na zero
                    hash |= ((int) digest[j]) & 0xFF;
                }
                result[index] = hash;
                index++;
            }
            //JAK się nam skończą hashe, znów generujemy tablicę, ale z saltem.
        }
        return result;
    }



    public void insert(String element) {
        elements++;
        byte[] bytes = element.getBytes(charset);
        int[] hashes = createHashes(bytes, numberOfHashes);
        for (int hash : hashes) {
            if (!filter[Math.abs(hash % filterSize)]) {
                size++;
            }
            filter[Math.abs(hash % filterSize)] = true;
        }
    }

    public boolean find(String element) {
        byte[] bytes = element.getBytes(charset);
        int[] hashes = createHashes(bytes, numberOfHashes);
        for (int hash : hashes) {
            if (!filter[(Math.abs(hash % filterSize))]) {
                return false;
            }
        }
        return true;
    }

    public double getBitsPerElement(double ProbabilityOfFailure) {
        return -1.44d*(Math.log(ProbabilityOfFailure) / Math.log(2));
    }

    public int getSize() {
        return size;
    }

    public int getElements() {
        return elements;
    }

    public int getFilterSize() {
        return filterSize;
    }

    public int getNumberOfHashes() {
        return numberOfHashes;
    }
}