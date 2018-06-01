package Lista5.zad3;

public class Main {

    public static void main(String[] args){
        if(args[0].equals("-k")){
            new Kruskal().run();
        } else if(args[0].equals("-p")){
            new Prim().run();
        } else {
            System.err.println("Wrong Start Argument!");
        }
    }
}
