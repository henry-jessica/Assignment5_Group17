package airportnetwork;

import java.util.*;
import java.util.function.Function;

public class AStar {

    public static List<Airport> findPath(Graph<Airport> graph, Airport start, Airport goal,
                                         Function<Airport, Double> heuristic) {
        Map<Airport, Double> gScore = new HashMap<>();
        Map<Airport, Double> fScore = new HashMap<>();
        Map<Airport, Airport> cameFrom = new HashMap<>();
        MiniHeap<Airport> openSet = new MiniHeap<>();

        // Initialise scores
        for (Airport node : graph.getAllNodes()) {
            gScore.put(node, Double.POSITIVE_INFINITY);
            fScore.put(node, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        fScore.put(start, heuristic.apply(start));
        openSet.insert(start, fScore.get(start));

        while (!openSet.isEmpty()) {
            Airport current = openSet.extractMin().key;

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (Flight flight : graph.getNeighbours(current)) {
                Airport neighbour = flight.getDestination();
                double tentativeG = gScore.get(current) + flight.getWeight();

                if (tentativeG < gScore.get(neighbour)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeG);
                    fScore.put(neighbour, tentativeG + heuristic.apply(neighbour));
                    openSet.insert(neighbour, fScore.get(neighbour));
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private static List<Airport> reconstructPath(Map<Airport, Airport> cameFrom, Airport current) {
        List<Airport> path = new LinkedList<>();
        while (current != null) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        return path;
    }
}
