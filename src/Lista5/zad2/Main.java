package Lista5.zad2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = readGraph();
        Vertex startVertex = getVertex(graph);
        Dijkstra.shortestPath(graph,startVertex);
        printPath(graph);
    }

    private static Graph readGraph(){
        Scanner scanner = new Scanner(System.in);
        Map<Integer,Vertex> vertex = new HashMap<>();
        int n = scanner.nextInt();
        for(int iteration = 1;iteration<=n;iteration++){
            vertex.put(iteration,new Vertex(iteration));
        }
        int m = scanner.nextInt();
        for (int iteration = 0; iteration < m; iteration++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            Vertex modified = vertex.get(source);
            modified.addOutgoingEdge(vertex.get(destination),weight);
            vertex.replace(source,modified);
        }
        Graph graph = new Graph();
        for (Map.Entry< Integer, Vertex> vertexEntry : vertex.entrySet()) {
            graph.addVertex(vertexEntry.getValue());
        }
        return graph;
    }

    private static void printPath(Graph graph){
        for (Vertex vertex : graph.getVertex()) {
            if(vertex.getDistance() == Integer.MAX_VALUE){
                System.out.println(vertex.getId() + " Infinity");

            } else {
                System.out.println(vertex.getId() + " " + vertex.getDistance());
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Vertex path:vertex.getShortestPath()) {
                stringBuilder.append("->" + path.getId());
            }
            System.err.println("Path to: "+vertex.getId()+" Path: " +stringBuilder.toString());
        }
    }

    private static Vertex getVertex(Graph graph){
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Iterator iterator= graph.getVertex().iterator();
        Vertex vertex = null;
        for(int iteration = 0;iteration<id;iteration++){
            vertex = (Vertex) iterator.next();
        }
        return vertex;
    }
}
