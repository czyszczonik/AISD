package Lista5.zad3;

import java.util.*;

public class Kruskal {

    public void run() {
        Graph graph = Graph.loadGraph();
        List<Edge> ready = Kruskal(graph);
        Graph.Print(ready);
    }


     public static List<Edge> Kruskal (Graph graph){
            List<Edge> mst = new LinkedList<>();
            DisjointSets disjointSet =
                    // size + 1 because we start counting from 1
                    new DisjointSets(graph.getVertices().size()+1);
            PriorityQueue priorityQueue = makeQueue(graph);

            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.remove().getEdge();

                //We Avoid cycles! it's dangerous for our tree.
                if (disjointSet.find(edge.getVertex1().getId()) ==
                        disjointSet.find(edge.getVertex2().getId())) {
                    continue;
                }

                //we Union two sets to one Mst set.
                disjointSet.union(edge.getVertex1().getId(), edge.getVertex2().getId());
                mst.add(edge);
            }
            return mst;
        }

        private static PriorityQueue makeQueue(Graph graph){
            PriorityQueue priorityQueue = new PriorityQueue();
            for (Edge e:graph.getEdges()) {
                priorityQueue.insert(e.getWeight(),e);
            }
            return priorityQueue;
        }
}