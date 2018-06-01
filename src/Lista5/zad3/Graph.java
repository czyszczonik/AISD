package Lista5.zad3;

import java.util.*;

public class Graph {
    private Set<Vertex> vertices = new HashSet<>();
    private Set<Edge> edges = new HashSet<>();

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }

    public static Graph loadGraph(){
        Graph graph = new Graph();
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

    public static void Print(List<Edge> list){
        for (Edge e : list) {
            if(e.getVertex1().getId() > e.getVertex2().getId()){
                System.out.println(e.getVertex2().getId() + " " + e.getVertex1().getId() + " " + e.getWeight());
            } else {
                System.out.println(e.getVertex1().getId() + " " + e.getVertex2().getId() + " " + e.getWeight());
            }
        }
    }

}
