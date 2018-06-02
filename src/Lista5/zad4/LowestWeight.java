package Lista5.zad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LowestWeight {
    private Random rand;
    private List<Edge> edges;
    private List<Vertex> vertices;
    int Memory;
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
        Vertex vertex = getRandomVertex();
        vertex.visit();
        Edge edge = getMinEdge(vertex);
        vertices.remove(vertex);
        steps++;
        Memory+=2;
        cost+=edge.getWeight();
        print(edge);
        while(vertices.size()-1 != 0){
            steps++;
            vertex = getNextVetrex(edge,vertex);
            edge = getMin(vertex.getEdges());
            print(edge);
            cost+=edge.getWeight();
        }
        long duration = (System.nanoTime() - startTime) / 1000;
        System.out.println(steps+" "+ cost+" "+ Memory+" "+duration);
    }

    private Vertex getNextVetrex(Edge edge, Vertex current){

        if(current == edge.getVertex1()){
            vertices.remove(edge.getVertex2());
            return edge.getVertex2();
        } else {
            vertices.remove(edge.getVertex1());
            return edge.getVertex1();
        }
    }

    private Edge getMinEdge(Vertex vertex){
        Edge edge = getMin(vertex.getEdges());
        Vertex testCase = getNextVetrex(edge,vertex);
        while(edge.getVertex2().isVisited() && edge.getVertex1().isVisited() && testCase.isVisited()){
            vertex.remove(edge);
            edge = getMin(vertex.getEdges());
        }
        edge.getVertex1().visit();
        edge.getVertex2().visit();
        return edge;
    }

    private Edge getMin(Set<Edge> edgeSet){
        Double priority = Double.MAX_VALUE;
        Edge edge = null;
        for (Edge e:edgeSet) {
            if(priority > e.getWeight() && !e.isVisited()){
                priority = e.getWeight();
                edge = e;
            }
        }
        edge.visit();
        return edge;
    }

    private void print(Edge edge){
        if(edge.getVertex1().getId() > edge.getVertex2().getId()){
            System.err.println("LW " + edge.getVertex2().getId()+" "+edge.getVertex1().getId()+" "+edge.getWeight());
        } else {
            System.err.println("LW " + edge.getVertex1().getId()+" "+edge.getVertex2().getId()+" "+edge.getWeight());

        }
    }
    private Vertex getRandomVertex() {
        return vertices.get(rand.nextInt(vertices.size()));
    }
}
