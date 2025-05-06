package airportnetwork;

import java.util.*;

/**
 * Represents a directed weighted graph using adjacency lists.
 * Supports typical graph operations such as insertion, removal, and traversal.
 *
 * @param <T> The type representing the vertices in the graph.
 */
public class Graph<T> {

    /**
     * Internal map representing the adjacency list of the graph.
     * Each key is a vertex, and the value is a list of outgoing edges.
     */
    private final Map<T, List<Edge<T>>> adjacencyList = new HashMap<>();

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int numVertices() {
        return adjacencyList.size();
    }

    /**
     * Returns an iterable over all vertices in the graph.
     *
     * @return iterable of all vertices
     */
    public Iterable<T> vertices() {
        return adjacencyList.keySet();
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return total number of edges
     */
    public int numEdges() {
        return edges().size();
    }

    /**
     * Returns a list of all edges in the graph.
     *
     * @return list containing all edges
     */
    public List<Edge<T>> edges() {
        List<Edge<T>> allEdges = new ArrayList<>();
        for (List<Edge<T>> edgeList : adjacencyList.values()) {
            allEdges.addAll(edgeList);
        }
        return allEdges;
    }

    /**
     * Retrieves the edge from vertex {@code u} to vertex {@code v}, if it exists.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @return the edge from u to v, or null if not found
     */
    public Edge<T> getEdge(T u, T v) {
        List<Edge<T>> edges = adjacencyList.getOrDefault(u, Collections.emptyList());
        for (Edge<T> edge : edges) {
            if (edge.getDestination().equals(v)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Returns an array containing the source and destination vertices of the given
     * edge.
     *
     * @param e the edge
     * @return an array of two vertices: [source, destination]
     */
    public T[] endVertices(Edge<T> e) {
        @SuppressWarnings("unchecked")
        T[] vertices = (T[]) new Object[] { e.getSource(), e.getDestination() };
        return vertices;
    }

    /**
     * Returns the vertex opposite to {@code v} on edge {@code e}.
     *
     * @param v the vertex
     * @param e the edge
     * @return the opposite vertex on the edge
     * @throws IllegalArgumentException if {@code v} is not connected to the edge
     */
    public T opposite(T v, Edge<T> e) {
        if (e.getSource().equals(v))
            return e.getDestination();
        if (e.getDestination().equals(v))
            return e.getSource();
        throw new IllegalArgumentException("Vertex is not incident to edge.");
    }

    /**
     * Returns the number of outgoing edges from the given vertex.
     *
     * @param v the vertex
     * @return number of outgoing edges
     */
    public int outDegree(T v) {
        return adjacencyList.getOrDefault(v, Collections.emptyList()).size();
    }

    /**
     * Returns the number of incoming edges to the given vertex.
     *
     * @param v the vertex
     * @return number of incoming edges
     */
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

    /**
     * Returns an iterable over all outgoing edges from the given vertex.
     *
     * @param v the vertex
     * @return iterable of outgoing edges
     */
    public Iterable<Edge<T>> outgoingEdges(T v) {
        return adjacencyList.getOrDefault(v, Collections.emptyList());
    }

    /**
     * Returns an iterable over all incoming edges to the given vertex.
     *
     * @param v the vertex
     * @return iterable of incoming edges
     */
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

    /**
     * Inserts a new vertex into the graph.
     *
     * @param x the vertex to add
     */
    public void insertVertex(T x) {
        addNode(x);
    }

    /**
     * Inserts a directed edge from {@code u} to {@code v} with the given weight.
     *
     * @param u      the source vertex
     * @param v      the destination vertex
     * @param weight the edge weight
     * @throws IllegalArgumentException if the edge already exists
     */
    public void insertEdge(T u, T v, double weight) {
        if (getEdge(u, v) != null) {
            throw new IllegalArgumentException("Edge already exists from " + u + " to " + v);
        }
        addEdge(u, v, weight);
    }

    /**
     * Removes a vertex and all its associated edges from the graph.
     *
     * @param v the vertex to remove
     */
    public void removeVertex(T v) {
        adjacencyList.remove(v);
        for (T u : adjacencyList.keySet()) {
            adjacencyList.get(u).removeIf(e -> e.getDestination().equals(v));
        }
    }

    /**
     * Removes the specified edge from the graph.
     *
     * @param e the edge to remove
     */
    public void removeEdge(Edge<T> e) {
        T u = e.getSource();
        List<Edge<T>> edges = adjacencyList.get(u);
        if (edges != null) {
            edges.remove(e);
        }
    }

    /**
     * Adds a new node to the graph if it does not already exist.
     *
     * @param node the vertex to add
     */
    public void addNode(T node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    /**
     * Adds a directed edge from source to destination with a weight.
     * If either vertex does not exist, it is added.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public void addEdge(T source, T destination, double weight) {
        addNode(source);
        addNode(destination);
        adjacencyList.get(source).add(new Edge<>(source, destination, weight));
    }

    /**
     * Returns the list of edges originating from the specified node.
     *
     * @param node the source vertex
     * @return list of outgoing edges
     */
    public List<Edge<T>> getEdges(T node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    /**
     * Returns the set of all vertices in the graph.
     *
     * @return set of all nodes
     */
    public Set<T> getAllNodes() {
        return adjacencyList.keySet();
    }
}