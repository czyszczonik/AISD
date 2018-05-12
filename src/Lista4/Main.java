package Lista4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if(args[1].equals("bst")){
            bst();
        } else if(args[1].equals("rbt")){
            rbt();
        } else if(args[1].equals("hmap")){

        }
    }
    private static void bst(){
        BST struct = new BST();
        Scanner scan = new Scanner(System.in);
        String[] line;
        int size = scan.nextInt()+1;
        for (int iteration = 0; iteration < size; iteration++) {
            line = scan.nextLine().split(" ");
            if(line[0].equals("insert")){
                struct.insert(cleanString(line[1]));
            } else if(line[0].equals("load")) {
                String[] strings = load(line[1]);
                for (String element:strings) {
                    struct.insert(element);
                }
            } else if(line[0].equals("delete")) {
                struct.delete(line[1]);
            } else if(line[0].equals("find")) {
                System.out.println(struct.find(line[1]));
            } else if(line[0].equals("min")) {
                System.out.println(struct.minValue(struct.getRoot()));
            } else if(line[0].equals("max")) {
                System.out.println(struct.maxValue(struct.getRoot()));
            } else if(line[0].equals("successor")) {
                System.out.println(struct.getSuccessor(line[1]));
            } else if(line[0].equals("inorder")) {
                System.out.println(struct.inOrder(struct.getRoot()));
            }
        }
    }

    private static void rbt(){
        RBTree struct = new RBTree();
        Scanner scan = new Scanner(System.in);
        String[] line;
        int size = scan.nextInt()+1;
        for (int iteration = 0; iteration < size; iteration++) {
            line = scan.nextLine().split(" ");
            if(line[0].equals("insert")){
                struct.insert(cleanString(line[1]));
            } else if(line[0].equals("load")) {
                String[] strings = load(line[1]);
                for (String element:strings) {
                    struct.insert(element);
                }
            } else if(line[0].equals("delete")) {
                struct.delete(line[1]);
            } else if(line[0].equals("find")) {
                System.out.println(struct.find(line[1]));
            } else if(line[0].equals("min")) {
                System.out.println(struct.minValue(struct.getRoot()));
            } else if(line[0].equals("max")) {
                System.out.println(struct.maxValue(struct.getRoot()));
            } else if(line[0].equals("successor")) {
                System.out.println(struct.getSuccessor(line[1]));
            } else if(line[0].equals("inorder")) {
                System.out.println(struct.inOrder(struct.getRoot()));
            }
        }
    }

    private static void hmap(){
        HashTable struct = new HashTable();
        Scanner scan = new Scanner(System.in);
        String[] line;
        int size = scan.nextInt()+1;
        for (int iteration = 0; iteration < size; iteration++) {
            line = scan.nextLine().split(" ");
            if(line[0].equals("insert")){
                struct.insert(cleanString(line[1]));
            } else if(line[0].equals("load")) {
                String[] strings = load(line[1]);
                for (String element:strings) {
                    struct.insert(element);
                }
            } else if(line[0].equals("delete")) {
                struct.delete(line[1]);
            } else if(line[0].equals("find")) {
                System.out.println(struct.find(line[1]));
            }
        }
    }


    private static String cleanString(String string){
        Pattern p = Pattern.compile("[^a-zA-Z]");
        String start = String.valueOf(string.charAt(0));
        if(p.matcher(start).find()){
            string = string.substring(1);
        }
        String end = String.valueOf(string.charAt(string.length()-1));
        if(p.matcher(end).find()){
            string = string.substring(0, string.length() - 1);
        }
        return string;
    }

    private static String[] load(String fileName) {
        String line;
        ArrayList<String> strings = new ArrayList<>();
        try {
            FileReader fileReader =
                    new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
        return strings.toArray(new String[strings.size()]);
    }
}
