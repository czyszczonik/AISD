package Lista5.zad4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Graph graph = loadGraph();
        new RandomPath(graph).run();
        new LowestWeightPath(graph).run();
    }

    public static Graph loadGraph(){
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);
        Map<Integer,Vertex> vertex = new HashMap<>();
        int n = scanner.nextInt();
        for(int iteration = 1;iteration<=n;iteration++){
            vertex.put(iteration,new Vertex(iteration));
        }
        int m = (n*(n-1))/2;
        for (int iteration = 0; iteration < m; iteration++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            Double weight = scanner.nextDouble();
            Vertex vertex1 = vertex.get(source);
            Vertex vertex2 = vertex.get(destination);
            Edge edge = new Edge(vertex1,vertex2,weight);
            graph.addEdge(edge);
            vertex1.addEdge(edge);
            vertex2.addEdge(edge);
            vertex.replace(source,vertex1);
            vertex.replace(destination,vertex2);
        }
        for (Map.Entry< Integer, Vertex> vertexEntry : vertex.entrySet()) {
            graph.addVertex(vertexEntry.getValue());
        }
        return graph;
    }
}
