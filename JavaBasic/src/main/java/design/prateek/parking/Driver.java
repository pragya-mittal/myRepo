package design.prateek.parking;

import java.io.Serializable;

public class Driver implements Serializable {
    String id;
    float avgRating;
    long numTrips;
    String name;

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public void setNumTrips(long numTrips) {
        this.numTrips = numTrips;
    }

    public Driver(String id, String name) {
        this.id = id;
        this.name = name;
        numTrips = 0;
        avgRating = 0;

    }

    public String getId() {
        return id;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public long getNumTrips() {
        return numTrips;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + avgRating;
    }

}