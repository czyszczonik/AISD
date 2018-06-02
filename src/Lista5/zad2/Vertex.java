package Lista5.zad2;

import java.util.*;

public class Vertex {

    private int id;


    private List<Vertex> shortestPath = new LinkedList<>();

    //Max integer value is Infinity weight(default);
    private Double pathWeight = Double.MAX_VALUE;

    //Outgoin' edgest from this Vertex to <Destination node,With specific weight>
    Set<Edge> outgoingEdges = new HashSet<>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addEdge(Edge edge) {
        outgoingEdges.add(edge);
    }

    public Double getPathWeight() {
        return pathWeight;
    }

    public void setPathWeight(Double pathWeight) {
        this.pathWeight = pathWeight;
    }

    public Set<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public List<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }
}