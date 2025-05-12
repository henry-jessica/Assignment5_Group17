package airportnetwork;
/**
 * Represents a flight connection between two airports.
*/
public class Flight {
    private final Airport source;
    private final Airport destination;
    private final double weight; // Can be distance, price, etc.
/**
     * Constructs a Flight object.
     */
    public Flight(Airport source, Airport destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
/**
     * Gets the weight of the flight.
     */
    public double getWeight() { return weight; }
    public Airport getDestination() { return destination; }
}
