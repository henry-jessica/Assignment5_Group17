package airportnetwork;

import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    public static Map<Airport, Double> computeShortestPath(Graph<Airport> graph, Airport start) {
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

            for (Flight flight : graph.getNeighbours(currentAirport)) {
                double newDist = distances.get(currentAirport) + flight.getWeight();
                if (newDist < distances.get(flight.getDestination())) {
                    distances.put(flight.getDestination(), newDist);
                    previous.put(flight.getDestination(), currentAirport);
                    minHeap.decreaseKey(flight.getDestination(), newDist);
                }
            }
        }

        return distances; // or return previous for path tracing
    }
}
