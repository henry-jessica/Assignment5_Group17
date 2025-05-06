package airportnetwork;

public class Airport {
    private final String code; // DUB
    private final String name;

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    // hashCode and equals overridden for HashMap/Set usage
    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Airport))
            return false;
        Airport other = (Airport) o;
        return this.code.equals(other.code);
    }

    @Override
    public String toString() {
        return code + " (" + name + ")";
    }
}
