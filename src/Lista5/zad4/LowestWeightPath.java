package Lista5.zad4;

import java.util.Set;

public class LowestWeightPath extends Path{

    public LowestWeightPath(Graph graph) {
        super();
        startTime = System.nanoTime();
        edges.addAll(graph.getEdges());
        vertices.addAll(graph.getVertices());
        Memory = edges.size() + vertices.size();

    }

    public void run(){
        Vertex vertex = getRandomVertex();
        Edge edge = getMinEdge(vertex);
        vertices.remove(vertex);
        steps++;
        Memory+=2;
        cost+=edge.getWeight();
//        print(edge);
        while(vertices.size()-1 != 0){
            steps++;
            vertex = getNextVertex(edge,vertex);
            edge = getMin(vertex.getEdges());
//            print(edge);
            cost+=edge.getWeight();
        }
        printStats("LW");
    }

    private Vertex getNextVertex(Edge edge, Vertex current){
        if(current == edge.getVertex1()){
            vertices.remove(edge.getVertex2());
            return edge.getVertex2();
        } else {
            vertices.remove(edge.getVertex1());
            return edge.getVertex1();
        }
    }

    private Edge getMinEdge(Vertex vertex){
        Edge edge = getMin(vertex.getEdges());
        Vertex testCase = getNextVertex(edge,vertex);
        while(edge.getVertex2().isVisited() &&
                edge.getVertex1().isVisited() &&
                testCase.isVisited()){

            vertex.remove(edge);
            edge = getMin(vertex.getEdges());

        }
        edge.getVertex1().visit();
        edge.getVertex2().visit();
        return edge;
    }

    private Edge getMin(Set<Edge> edgeSet){
        Double priority = Double.MAX_VALUE;
        Edge edge = null;
        for (Edge e:edgeSet) {
            if(priority > e.getWeight() && !e.isVisited()){
                priority = e.getWeight();
                edge = e;
            }
        }
        edge.visit();
        return edge;
    }

    private void print(Edge edge){
        super.print(edge,"LW");
    }


}
