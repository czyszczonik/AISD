package Lista5.zad3;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private int id;
    private Set<Edge> edges = new HashSet<>();
    private boolean visited = false;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }
}
