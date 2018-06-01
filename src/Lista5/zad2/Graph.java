package Lista5.zad2;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Vertex> vertex = new HashSet<>();

    public void addVertex(Vertex vertex) {
        this.vertex.add(vertex);
    }

    public Set<Vertex> getVertex() {
        return vertex;
    }

    public void setVertex(Set<Vertex> vertex) {
        this.vertex = vertex;
    }
}