package design.CabCompany;

public class Location {
    String source;
    String destination;

    public Location(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Location getLocation(String source, String destination) {
        return new Location(source,destination);
    }
}
