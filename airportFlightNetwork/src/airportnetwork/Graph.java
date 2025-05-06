package airportnetwork;

import java.util.*;

public class Graph<T> {
    private final Map<T, List<Edge<T>>> adjacencyList = new HashMap<>();

    public int numVertices() {
        return adjacencyList.size();
    }

    public Iterable<T> vertices() {
        return adjacencyList.keySet();
    }

    public int numEdges() {
        return edges().size();
    }

    public List<Edge<T>> edges() {
        List<Edge<T>> allEdges = new ArrayList<>();
        for (List<Edge<T>> edgeList : adjacencyList.values()) {
            allEdges.addAll(edgeList);
        }
        return allEdges;
    }

    public Edge<T> getEdge(T u, T v) {
        List<Edge<T>> edges = adjacencyList.getOrDefault(u, Collections.emptyList());
        for (Edge<T> edge : edges) {
            if (edge.getDestination().equals(v)) {
                return edge;
            }
        }
        return null;
    }

    public T[] endVertices(Edge<T> e) {
        @SuppressWarnings("unchecked")
        T[] vertices = (T[]) new Object[] { e.getSource(), e.getDestination() };
        return vertices;
    }

    public T opposite(T v, Edge<T> e) {
        if (e.getSource().equals(v))
            return e.getDestination();
        if (e.getDestination().equals(v))
            return e.getSource();
        throw new IllegalArgumentException("Vertex is not incident to edge.");
    }

    public int outDegree(T v) {
        return adjacencyList.getOrDefault(v, Collections.emptyList()).size();
    }

    public int inDegree(T v) {
        int inCount = 0;
        for (T u : adjacencyList.keySet()) {
            for (Edge<T> e : adjacencyList.get(u)) {
                if (e.getDestination().equals(v)) {
                    inCount++;
                }
            }
        }
        return inCount;
    }

    public Iterable<Edge<T>> outgoingEdges(T v) {
        return adjacencyList.getOrDefault(v, Collections.emptyList());
    }

    public Iterable<Edge<T>> incomingEdges(T v) {
        List<Edge<T>> incoming = new ArrayList<>();
        for (T u : adjacencyList.keySet()) {
            for (Edge<T> e : adjacencyList.get(u)) {
                if (e.getDestination().equals(v)) {
                    incoming.add(e);
                }
            }
        }
        return incoming;
    }

    public void insertVertex(T x) {
        addNode(x);
    }

    public void insertEdge(T u, T v, double weight) {
        if (getEdge(u, v) != null) {
            throw new IllegalArgumentException("Edge already exists from " + u + " to " + v);
        }
        addEdge(u, v, weight);
    }

    public void removeVertex(T v) {
        adjacencyList.remove(v);
        for (T u : adjacencyList.keySet()) {
            adjacencyList.get(u).removeIf(e -> e.getDestination().equals(v));
        }
    }

    public void removeEdge(Edge<T> e) {
        T u = e.getSource();
        List<Edge<T>> edges = adjacencyList.get(u);
        if (edges != null) {
            edges.remove(e);
        }
    }

    public void addNode(T node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(T source, T destination, double weight) {
        addNode(source);
        addNode(destination);
        adjacencyList.get(source).add(new Edge<>(source, destination, weight));
    }

    public List<Edge<T>> getEdges(T node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    public Set<T> getAllNodes() {
        return adjacencyList.keySet();
    }
}