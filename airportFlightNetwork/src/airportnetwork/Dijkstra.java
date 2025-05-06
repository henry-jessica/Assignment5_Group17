package airportnetwork;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    public static Map<Airport, Double> computeShortestPath(Graph<Airport> graph, Airport start) {
        Map<Airport, Double> distances = new HashMap<>();
        MiniHeap<Airport> minHeap = new MiniHeap<>();

        for (Airport airport : graph.getAllNodes()) {
            distances.put(airport, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);
        minHeap.insert(start, 0.0);

        while (!minHeap.isEmpty()) {
            MiniHeap.Node<Airport> current = minHeap.extractMin();
            Airport currentAirport = current.key;

            for (Edge<Airport> flight : graph.getEdges(currentAirport)) {
                Airport dest = flight.getDestination();
                double newDist = distances.get(currentAirport) + flight.getWeight();

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

        return distances;
    }

    public static double findShortestDistance(Graph<Airport> graph, Airport start, Airport end) {
        Map<Airport, Double> distances = computeShortestPath(graph, start);
        return distances.getOrDefault(end, Double.POSITIVE_INFINITY);
    }

    public static List<Airport> getPath(Graph<Airport> graph, Airport start, Airport goal) {
        Map<Airport, Double> distances = new HashMap<>();
        Map<Airport, Airport> previous = new HashMap<>();
        MiniHeap<Airport> minHeap = new MiniHeap<>();

        for (Airport airport : graph.getAllNodes()) {
            distances.put(airport, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);
        minHeap.insert(start, 0.0);

        while (!minHeap.isEmpty()) {
            MiniHeap.Node<Airport> current = minHeap.extractMin();
            Airport currentAirport = current.key;

            for (Edge<Airport> flight : graph.getEdges(currentAirport)) {
                Airport dest = flight.getDestination();
                double newDist = distances.get(currentAirport) + flight.getWeight();

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

        if (!distances.containsKey(goal) || distances.get(goal) == Double.POSITIVE_INFINITY) {
            return Collections.emptyList();
        }

        LinkedList<Airport> path = new LinkedList<>();
        Airport current = goal;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }

        return path;
    }
}