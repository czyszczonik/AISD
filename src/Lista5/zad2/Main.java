package Lista5.zad2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph graph = readGraph();
        Vertex startVertex = getVertex(graph);
        Dijkstra.run(graph,startVertex);
        printPath(graph);
    }

    private static Graph readGraph(){
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);
        List<Vertex> vertices = new ArrayList<>();
        int n = scanner.nextInt();
        for(int iteration = 0;iteration<n;iteration++){
            vertices.add(iteration,new Vertex(iteration+1));
        }
        int m = scanner.nextInt();
        for (int iteration = 1; iteration <= m; iteration++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            double weight = scanner.nextDouble();
            Vertex start = vertices.get(source-1);
            Vertex end = vertices.get(destination-1);
            start.addEdge(new Edge(end,weight));
        }
        graph.setVertex(new HashSet<>(vertices));
        return graph;
    }

    private static void printPath(Graph graph){
        for (Vertex vertex : graph.getVertex()) {
            if(vertex.getPathWeight() == Integer.MAX_VALUE){
                System.out.println(vertex.getId() + " Infinity");

            } else {
                System.out.println(vertex.getId() + " " + vertex.getPathWeight());
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
        for (Vertex item:graph.getVertex()) {
            if(item.getId() == id){
                return item;
            }
        }
        throw new RuntimeException("Element Not found");
    }
}
