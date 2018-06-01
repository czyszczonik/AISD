package Lista5.zad2;

public class HeapItem {
    private Double priority;
    private Vertex vertex;

    public HeapItem(Double priority, Vertex vertex) {
        this.priority = priority;
        this.vertex = vertex;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public Vertex getVertex() {
        return vertex;
    }
}
