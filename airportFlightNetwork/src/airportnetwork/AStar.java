package airportnetwork;

import java.util.*;
import java.util.function.Function;

public class AStar {

    /**
     * Finds the shortest path between a start and goal airport in a graph using the A* search algorithm.
    */
    public static List<Airport> findPath(Graph<Airport> graph, Airport start, Airport goal,
            Function<Airport, Double> heuristic) {
         // gScore: Cost from start along the shortest known path to this airport.
        Map<Airport, Double> gScore = new HashMap<>();
        Map<Airport, Double> fScore = new HashMap<>();
        Map<Airport, Airport> cameFrom = new HashMap<>();
        MiniHeap<Airport> openSet = new MiniHeap<>();

        // Initialize gScore and fScore to infinity for all nodes, indicating that initially no path is known.
        for (Airport node : graph.getAllNodes()) {
            gScore.put(node, Double.POSITIVE_INFINITY);
            fScore.put(node, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        fScore.put(start, heuristic.apply(start));
        openSet.insert(start, fScore.get(start));
// The main loop of the A* algorithm. It continues as long as there are nodes left to evaluate in the open set.
        while (!openSet.isEmpty()) {
            Airport current = openSet.extractMin().key;

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }
// Iterate over all the neighbors of the current node.
            for (Edge<Airport> flight : graph.getEdges(current)) {
                Airport neighbour = flight.getDestination();
                double tentativeG = gScore.get(current) + flight.getWeight();

                if (tentativeG < gScore.get(neighbour)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeG);
                    fScore.put(neighbour, tentativeG + heuristic.apply(neighbour));

                    if (openSet.contains(neighbour)) {
                        openSet.decreaseKey(neighbour, fScore.get(neighbour));
                    } else {
                        openSet.insert(neighbour, fScore.get(neighbour));
                    }
                }
            }
        }
// If the open set is empty and we haven't reached the goal, it means there's no path from start to goal.
        return Collections.emptyList();
    }
/**
     * Computes the distances from a start airport to all other airports in the graph using a modified A* algorithm
     * (similar to Dijkstra's algorithm, as it doesn't explicitly stop at a goal).
     */
    public static Map<Airport, Double> computeDistances(Graph<Airport> graph, Airport start,
            Function<Airport, Double> heuristic) {
        Map<Airport, Double> gScore = new HashMap<>();
        Map<Airport, Double> fScore = new HashMap<>();
        MiniHeap<Airport> openSet = new MiniHeap<>();
// Initialize gScore and fScore to infinity for all nodes.
        for (Airport node : graph.getAllNodes()) {
            gScore.put(node, Double.POSITIVE_INFINITY);
            fScore.put(node, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        fScore.put(start, heuristic.apply(start));
        openSet.insert(start, fScore.get(start));
// Main loop, continues until all reachable nodes have been evaluated.
        while (!openSet.isEmpty()) {
            Airport current = openSet.extractMin().key;

            for (Edge<Airport> flight : graph.getEdges(current)) {
                Airport neighbour = flight.getDestination();
                double tentativeG = gScore.get(current) + flight.getWeight();

                if (tentativeG < gScore.get(neighbour)) {
                    gScore.put(neighbour, tentativeG);
                    fScore.put(neighbour, tentativeG + heuristic.apply(neighbour));

                    if (openSet.contains(neighbour)) {
                        openSet.decreaseKey(neighbour, fScore.get(neighbour));
                    } else {
                        openSet.insert(neighbour, fScore.get(neighbour));
                    }
                }
            }
        }

        // Return the map of distances from the start node.
        return gScore;
    }
    /**
     * Reconstructs the path from the start node to the current node using the cameFrom map.
     */

    private static List<Airport> reconstructPath(Map<Airport, Airport> cameFrom, Airport current) {
        List<Airport> path = new LinkedList<>();
        while (current != null) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        return path;
    }
}
