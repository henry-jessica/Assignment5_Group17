package airportnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
    private final Map<T, List<Flight>> adjacencyList = new HashMap<>();

    public void addNode(T node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(T source, T destination, double weight) {
        adjacencyList.get(source).add(new Flight((Airport) source, (Airport) destination, weight));
    }

    public List<Flight> getNeighbours(T node) {
        return adjacencyList.get(node);
    }

    public Set<T> getAllNodes() {
        return adjacencyList.keySet();
    }
}

