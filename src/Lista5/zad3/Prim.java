package Lista5.zad3;


import java.util.*;

class Prim{

    public void run(){
        Graph graph = Graph.loadGraph();
        Vertex start = getVertex(graph);
        List<Edge> mst = Prime(graph,start);
        Graph.Print(mst);
    }

    private static List<Edge> Prime(Graph graph, Vertex StartVertex){
        List<Integer> vertexList = new ArrayList<>();
        PriorityQueue queue = new PriorityQueue();

        List<Edge> mst = new ArrayList<>();
        Set<Edge> edges = new HashSet<>();
        edges.addAll(graph.getEdges());
        vertexList.add(StartVertex.getId());
        for (Edge edge:StartVertex.getEdges()) {
            if (edge.getVertex1().getId() == StartVertex.getId()) {
                queue.insert(edge.getWeight(), edge);
            } else {
                queue.insert(edge.getWeight(), edge);
            }
        }
        StartVertex.visit();
        Edge edge;
        while(!queue.isEmpty()){
            edge = queue.remove().getEdge();
            if((!edge.getVertex1().isVisited() ||
                    !edge.getVertex2().isVisited()) &&
                    edges.contains(edge)){
                mst.add(edge);
                edges.remove(edge);
                if(edge.getVertex1().isVisited()){
                    for (Edge e: edge.getVertex2().getEdges()) {
                        if (e.getVertex1().getId() == edge.getVertex1().getId()) {
                            queue.insert(e.getWeight(), e);
                        } else {
                            queue.insert(e.getWeight(), e);
                        }
                    }
                    edge.getVertex2().visit();

                } else {
                    //Adding Vertex2 Edges to Queue
                    for (Edge e: edge.getVertex1().getEdges()) {
                        if (e.getVertex2().getId() == edge.getVertex2().getId()) {
                            queue.insert(e.getWeight(), e);
                        } else {
                            queue.insert(e.getWeight(), e);
                        }
                    }
                    edge.getVertex1().visit();
                }
            }
        }
        return mst;
    }

    private static Vertex getVertex(Graph graph){
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Iterator iterator= graph.getVertices().iterator();
        Vertex vertex = null;
        for(int iteration = 0;iteration<id;iteration++){
            vertex = (Vertex) iterator.next();
        }
        return vertex;
    }
}
