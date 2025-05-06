package airportnetwork;

import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    public static Map<Graph.Vertex<Airport>, Double> computeShortestPath(Graph<Airport, Double> graph,
            Graph.Vertex<Airport> start) {
        Map<Graph.Vertex<Airport>, Double> distances = new HashMap<>();
        Map<Graph.Vertex<Airport>, Graph.Vertex<Airport>> previous = new HashMap<>();
        MiniHeap<Graph.Vertex<Airport>> minHeap = new MiniHeap<>();

        for (Graph.Vertex<Airport> vertex : graph.vertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);
        minHeap.insert(start, 0.0);

        while (!minHeap.isEmpty()) {
            MiniHeap.Node<Graph.Vertex<Airport>> current = minHeap.extractMin();
            Graph.Vertex<Airport> currentVertex = current.key;

            for (Graph.Edge<Double> edge : graph.outgoingEdges(currentVertex)) {
                Graph.Vertex<Airport> neighbor = graph.opposite(currentVertex, edge);
                double newDist = distances.get(currentVertex) + edge.getElement();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentVertex);
                    minHeap.decreaseKey(neighbor, newDist);
                }
            }
        }

        return distances; // optionally return `previous` for path reconstruction
    }
}