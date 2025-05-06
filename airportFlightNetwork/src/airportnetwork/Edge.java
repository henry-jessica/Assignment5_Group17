package airportnetwork;

/**
 * Represents a weighted directed edge in a graph, from a source vertex to a
 * destination vertex.
 *
 * @param <T> The type representing vertices in the graph.
 */
public class Edge<T> {

    /** The source vertex of the edge. */
    private final T source;

    /** The destination vertex of the edge. */
    private final T destination;

    /** The weight associated with the edge. */
    private final double weight;

    /**
     * Constructs an edge with the given source, destination, and weight.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public Edge(T source, T destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Returns the source vertex of the edge.
     *
     * @return the source vertex
     */
    public T getSource() {
        return source;
    }

    /**
     * Returns the destination vertex of the edge.
     *
     * @return the destination vertex
     */
    public T getDestination() {
        return destination;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the edge weight
     */
    public double getWeight() {
        return weight;
    }
}