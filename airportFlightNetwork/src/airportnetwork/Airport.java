package airportnetwork;
/**
 * Represents an airport.
 * An Airport has a unique code (e.g., "DUB" for Dublin Airport) and a name.
 */
public class Airport {
    private final String code; // The unique code of the airport
    private final String name;// The full name of the airport

    /**
     * Constructs an Airport object.
     */
    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }
/**
     * Gets the 3-letter code of the airport.
     */
    public String getCode() { return code; }

    // hashCode and equals overridden for HashMap/Set usage
    @Override
    public int hashCode() { return code.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport other = (Airport) o;
        return this.code.equals(other.code);
    }
/**
     * Overrides the toString() method to provide a string representation of the Airport object.
     * This makes it easier to print Airport objects in a readable format.
     */
    @Override
    public String toString() {
        return code + " (" + name + ")";
    }
}
