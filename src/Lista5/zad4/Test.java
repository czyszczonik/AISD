package Lista5.zad4;

import java.util.*;

public class Test {
    public static void main(String[] args){
        new Test().test();
    }

    Random random = new Random();
    private void test(){
        Graph graph;
        for (int iteration = 10; iteration <= 1000; iteration+=10) {
            graph= makeGraph(iteration);
            new RandomPath(graph).run();
            new LowestWeightPath(graph).run();
        }
    }

    private Graph makeGraph(int size){
        Graph graph = new Graph();
        Vertex vertex;
        for (int iteration = 1; iteration <= size; iteration++) {
            vertex = new Vertex(iteration);
            graph.addVertex(vertex);
        }
        addEdges(graph,size);
        return graph;
    }
    private void addEdges(Graph graph, int size){
        List<Vertex> vertex = new LinkedList<>();
        vertex.addAll(graph.getVertices());
        Vertex current;
        Edge edge;
        for (int iterator = 0; iterator < size; iterator++) {
            current = vertex.get(iterator);
            for (int index = iterator+1; index < size;index++){
                edge = new Edge(current,vertex.get(index),random.nextDouble());
                graph.addEdge(edge);
                current.addEdge(edge);
                vertex.get(index).addEdge(edge);
            }
        }
        Set<Vertex> finalSet = new HashSet<>(vertex);
        graph.setVertices(finalSet);
    }
}
