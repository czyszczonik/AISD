package Lista5.zad2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vertex {

    private int id;

    private List<Vertex> shortestPath = new LinkedList<>();

    //Max integer value is Infinity weight(default);
    private Double distance = Double.MAX_VALUE;

    //Outgoin' edgest from this Vertex to <Destination node,With specific weight>
    Map<Vertex, Integer> outgoingEdges = new HashMap<>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addOutgoingEdge(Vertex destination, int distance) {
        outgoingEdges.put(destination, distance);
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Vertex, Integer> getOutgoingEdges() {
        return outgoingEdges;
    }

    public List<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }
}