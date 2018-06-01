package Lista5.zad4;


public class Edge {
    private Vertex vertex1;
    private Vertex vertex2;
    private Double weight;

    public Edge(Vertex vertex1, Vertex vertex2, Double weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public Double getWeight() {
        return weight;
    }

}
