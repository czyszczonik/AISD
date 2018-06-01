package Lista5.zad4;

public class HeapItem {
    private Double priority;
    private Edge edge;

    public HeapItem(Double priority, Edge edge) {
        this.priority = priority;
        this.edge = edge;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public Edge getEdge() {
        return edge;
    }
}
