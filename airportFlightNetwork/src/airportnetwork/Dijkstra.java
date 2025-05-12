package airportnetwork;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dijkstra {
    /**
     * Computes the shortest path distances from a start airport to all other airports
     * in the graph using Dijkstra's algorithm.
     */

    public static Map<Airport, Double> computeShortestPath(Graph<Airport> graph, Airport start) {
        // distances: Stores the shortest distance from the start airport to each airport.
        Map<Airport, Double> distances = new HashMap<>();
        MiniHeap<Airport> minHeap = new MiniHeap<>();

        // Initialize distances to infinity for all airports, indicating that initially
        for (Airport airport : graph.getAllNodes()) {
            distances.put(airport, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);// The distance from the start airport to itself is 0.
        minHeap.insert(start, 0.0);
// Dijkstra's algorithm main loop: Continue as long as there are airports
        while (!minHeap.isEmpty()) {
            MiniHeap.Node<Airport> current = minHeap.extractMin();
            Airport currentAirport = current.key;

            for (Edge<Airport> flight : graph.getEdges(currentAirport)) {
                Airport dest = flight.getDestination();
                double newDist = distances.get(currentAirport) + flight.getWeight();
// If the newly calculated distance is shorter than the current shortest distance
                if (newDist < distances.get(dest)) {
                    distances.put(dest, newDist);
                    if (minHeap.contains(dest)) {
                        minHeap.decreaseKey(dest, newDist);
                    } else {
                        minHeap.insert(dest, newDist);
                    }
                }
            }
        }
// Return the map containing the shortest distances from the start airport
        return distances;
    }
/**
     * Finds the shortest distance between a start and end airport in the graph.
     */
    public static double findShortestDistance(Graph<Airport> graph, Airport start, Airport end) {
        Map<Airport, Double> distances = computeShortestPath(graph, start);
        return distances.getOrDefault(end, Double.POSITIVE_INFINITY);
    }

    /**
     * Retrieves the shortest path (list of airports) from a start airport to a goal
     * airport in the graph using Dijkstra's algorithm.
     */

    public static List<Airport> getPath(Graph<Airport> graph, Airport start, Airport goal) {
        Map<Airport, Double> distances = new HashMap<>();
        Map<Airport, Airport> previous = new HashMap<>();
        MiniHeap<Airport> minHeap = new MiniHeap<>();
 // Initialize distances to infinity for all airports.
        for (Airport airport : graph.getAllNodes()) {
            distances.put(airport, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);
        minHeap.insert(start, 0.0);
// Dijkstra's algorithm main loop.
        while (!minHeap.isEmpty()) {
            MiniHeap.Node<Airport> current = minHeap.extractMin();
            Airport currentAirport = current.key;

            for (Edge<Airport> flight : graph.getEdges(currentAirport)) {
                Airport dest = flight.getDestination();
                double newDist = distances.get(currentAirport) + flight.getWeight();
// If a shorter path to the destination airport is found.
                if (newDist < distances.get(dest)) {
                    distances.put(dest, newDist);
                    previous.put(dest, currentAirport);
                    if (minHeap.contains(dest)) {
                        minHeap.decreaseKey(dest, newDist);
                    } else {
                        minHeap.insert(dest, newDist);
                    }
                }
            }
        }
// If the goal airport is not reachable from the start airport, return an empty list.
        if (!distances.containsKey(goal) || distances.get(goal) == Double.POSITIVE_INFINITY) {
            return Collections.emptyList();
        }

        LinkedList<Airport> path = new LinkedList<>();
        Airport current = goal;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }
 // Return the reconstructed shortest path.
        return path;
    }
}
