package Lista5.zad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPath {

    private Random rand;
    private List<Edge> edges;
    private List<Vertex> vertices;
    int Memory = 0;
    int cost = 0;
    int steps = 0;
    long startTime;

    public RandomPath(Graph graph) {
        startTime = System.nanoTime();
        rand = new Random();
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        edges.addAll(graph.getEdges());
        vertices.addAll(graph.getVertices());
        Memory = edges.size() + vertices.size();

    }
    public void run(){
        Edge edge;
        Memory++;
        while(!vertices.isEmpty()){
            edge = getRandomEdge();
            vertices.remove(edge.getVertex1());
            vertices.remove(edge.getVertex2());
            cost+=edge.getWeight();
            print(edge);
            steps++;
        }
        long duration = (System.nanoTime() - startTime) / 1000;

        System.out.println(steps+" "+ cost+" "+ Memory+" "+duration);
    }

    private Edge getRandomEdge() {
        return edges.get(rand.nextInt(edges.size()));
    }

    private void print(Edge edge){
        if(edge.getVertex1().getId() > edge.getVertex2().getId()){
            System.err.println("R " + edge.getVertex2().getId()+" "+edge.getVertex1().getId()+" "+edge.getWeight());
        } else {
            System.err.println("R " + edge.getVertex1().getId()+" "+edge.getVertex2().getId()+" "+edge.getWeight());

        }
    }
}
