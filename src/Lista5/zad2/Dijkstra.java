package Lista5.zad2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Dijkstra {

    public static Graph run(Graph graph, Vertex source) {
        source.setPathWeight(0d);
        Set<Vertex> visitedVertex = new HashSet<>();
        PriorityQueue vertexQueue = new PriorityQueue();
        vertexQueue.insert(0d,source);

        //repeat while algorithm have vertex to visit.
        while (vertexQueue.getSize() != 0) {
            Vertex currentVertex = vertexQueue.remove().getVertex();
            //for all outgoing edges repeat algorithm
            for (Edge edge : currentVertex.getOutgoingEdges()) {
                Vertex destinationVertex = edge.getDestination();
                double edgeWeight = edge.getWeight();
                //if vertex wasn't visited
                if (!visitedVertex.contains(destinationVertex)) {
                    //calculate new shortest path to destinationVertex
                    shortestPath(destinationVertex,currentVertex,edgeWeight);
                    //add destinationVertex to Queue
                    vertexQueue.insert(destinationVertex.getPathWeight(),destinationVertex);
                }
            }
            visitedVertex.add(currentVertex);
        }
        return graph;
    }

    private static void shortestPath(Vertex destination, Vertex source, double currentWeight) {
        double pathWeight = source.getPathWeight();
        //We find shorter path!
        if (pathWeight + currentWeight < destination.getPathWeight()) {
            //getting previous path
            LinkedList<Vertex> newPath =
                    new LinkedList<>(source.getShortestPath());
            //adding source vertex to newPath.
            newPath.add(source);
            //Set new properties of destination vertex (new distance and path)
            destination.setPathWeight(pathWeight + currentWeight);
            destination.setShortestPath(newPath);
        }
    }


}
