package Lista5.zad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LowestWeight {
    private Random rand;
    private List<Edge> edges;
    private List<Vertex> vertices;
    int Memory = 0;
    int cost = 0;
    int steps = 0;
    long startTime;

    public LowestWeight(Graph graph) {
        startTime = System.nanoTime();
        rand = new Random();
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        edges.addAll(graph.getEdges());
        vertices.addAll(graph.getVertices());
        Memory = edges.size() + vertices.size();

    }
    public void run(){
        Vertex start = getRandomVertex();
        vertices.remove(start);
        while(!vertices.isEmpty()){

        }
        long duration = (System.nanoTime() - startTime) / 1000;
        System.out.println(steps+" "+ cost+" "+ Memory+" "+duration);
    }
    public void print(Edge edge){
        if(edge.getVertex1().getId() > edge.getVertex2().getId()){
            System.err.println(edge.getVertex2().getId()+" "+edge.getVertex1().getId()+" "+edge.getWeight());
        } else {
            System.err.println(edge.getVertex1().getId()+" "+edge.getVertex2().getId()+" "+edge.getWeight());

        }
    }
    public Vertex getRandomVertex() {
        return vertices.get(rand.nextInt(vertices.size()));
    }
}
