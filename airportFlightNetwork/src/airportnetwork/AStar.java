package airportnetwork;

import java.util.*;
import java.util.function.Function;

public class AStar {

    public static List<Airport> findPath(Graph<Airport, Double> graph,
            Graph.Vertex<Airport> start,
            Graph.Vertex<Airport> goal,
            Function<Airport, Double> heuristic) {

        Map<Graph.Vertex<Airport>, Double> gScore = new HashMap<>();
        Map<Graph.Vertex<Airport>, Double> fScore = new HashMap<>();
        Map<Graph.Vertex<Airport>, Graph.Vertex<Airport>> cameFrom = new HashMap<>();
        MiniHeap<Graph.Vertex<Airport>> openSet = new MiniHeap<>();

        for (Graph.Vertex<Airport> v : graph.vertices()) {
            gScore.put(v, Double.POSITIVE_INFINITY);
            fScore.put(v, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        fScore.put(start, heuristic.apply(start.getElement()));
        openSet.insert(start, fScore.get(start));

        while (!openSet.isEmpty()) {
            Graph.Vertex<Airport> current = openSet.extractMin().key;

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (Graph.Edge<Double> edge : graph.outgoingEdges(current)) {
                Graph.Vertex<Airport> neighbor = graph.opposite(current, edge);
                double tentativeG = gScore.get(current) + edge.getElement();

                if (tentativeG < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeG);
                    fScore.put(neighbor, tentativeG + heuristic.apply(neighbor.getElement()));
                    openSet.insert(neighbor, fScore.get(neighbor));
                }
            }
        }

        return Collections.emptyList();
    }

    private static List<Airport> reconstructPath(Map<Graph.Vertex<Airport>, Graph.Vertex<Airport>> cameFrom,
            Graph.Vertex<Airport> current) {
        List<Airport> path = new LinkedList<>();
        while (current != null) {
            path.add(0, current.getElement());
            current = cameFrom.get(current);
        }
        return path;
    }
}