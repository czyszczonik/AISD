package Lista5.zad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Path{

    protected Random rand;
    protected List<Edge> edges;
    protected List<Vertex> vertices;
    protected int Memory;
    protected Double cost = 0d;
    protected int steps = 0;
    protected long startTime;

    protected Path(){
            rand = new Random();
            edges = new ArrayList<>();
            vertices = new ArrayList<>();
        }

    protected Vertex getRandomVertex() {
        return vertices.get(rand.nextInt(vertices.size()));
    }

    protected Edge getRandomEdge() {
        return edges.get(rand.nextInt(edges.size()));
    }

    protected abstract void run();

    protected void print(Edge edge, String type){
        if(edge.getVertex1().getId() > edge.getVertex2().getId()){
            System.err.println(type+" " + edge.getVertex2().getId()+" "+edge.getVertex1().getId()+" "+edge.getWeight());
        } else {
            System.err.println(type+" " + edge.getVertex1().getId()+" "+edge.getVertex2().getId()+" "+edge.getWeight());

        }
    }

    protected void printStats(String s){
        long duration = (System.nanoTime() - startTime) / 1000;
        System.out.println(s+" "+steps+" "+ cost+" "+ Memory+" "+duration);
    }

}
