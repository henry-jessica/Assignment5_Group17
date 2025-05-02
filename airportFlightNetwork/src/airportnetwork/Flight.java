package airportnetwork;

public class Flight {
    private final Airport source;
    private final Airport destination;
    private final double weight; // Can be distance, price, etc.

    public Flight(Airport source, Airport destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public double getWeight() { return weight; }
    public Airport getDestination() { return destination; }
}
