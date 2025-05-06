package airportnetwork;

public class Edge<T> {
    private final T source;
    private final T destination;
    private final double weight;

    public Edge(T source, T destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public T getSource() {
        return source;
    }

    public T getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}