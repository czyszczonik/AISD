package Lista5.zad4;

public class RandomPath extends Path{

    public RandomPath(Graph graph) {
        super();
        startTime = System.nanoTime();
        edges.addAll(graph.getEdges());
        vertices.addAll(graph.getVertices());
        Memory = edges.size() + vertices.size();
    }

    public void run(){
        Edge edge;
        Memory++;
        while(!vertices.isEmpty()){
            edge = getRandomEdge();
            vertices.remove(edge.getVertex1());
            vertices.remove(edge.getVertex2());
            cost+=edge.getWeight();
            print(edge);
            steps++;
        }
        printStats("R");
    }

    private void print(Edge edge) {
        print(edge, "R");
    }
}
