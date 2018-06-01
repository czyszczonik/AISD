package Lista5.zad2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    public static Graph shortestPath (Graph graph, Vertex source) {
        source.setDistance(0d);
        Set<Vertex> visitedVertex = new HashSet<>();
        PriorityQueue vertexQueue = new PriorityQueue();
        vertexQueue.insert(0d,source);

        //repeat while algorithm have vertex to visit.
        while (vertexQueue.getSize() != 0) {
            Vertex currentVertex = vertexQueue.remove().getVertex();
            //for all outgoing edges repeat algorithm
            for (Map.Entry< Vertex, Integer> outgoingEdge : currentVertex.getOutgoingEdges().entrySet()) {
                Vertex destinationVertex = outgoingEdge.getKey();
                Integer edgeWeight = outgoingEdge.getValue();
                //if vertex wasn't visited
                if (!visitedVertex.contains(destinationVertex)) {
                    //calculate new shortest path to destinationVertex
                    calculateShortestPath(destinationVertex,
                            edgeWeight,
                            currentVertex);
                    //add destinationVertex to Queue
                    vertexQueue.insert(destinationVertex.getDistance()
                            ,destinationVertex);
                }
            }
            visitedVertex.add(currentVertex);
        }
        return graph;
    }

    private static void calculateShortestPath(Vertex destinationVertex,
                                              Integer edgeWeigh,
                                              Vertex sourceVertex) {
        Double sourceDistance = sourceVertex.getDistance();
        //We find shortest path!
        if (sourceDistance + edgeWeigh < destinationVertex.getDistance()) {
            //getting previous path
            LinkedList<Vertex> newPath =
                    new LinkedList<>(sourceVertex.getShortestPath());
            //adding source vertex to newPath.
            newPath.add(sourceVertex);
            //Set new properties of destination vertex (new distance and path)
            destinationVertex.setDistance(sourceDistance + edgeWeigh);
            destinationVertex.setShortestPath(newPath);
        }
    }
}
