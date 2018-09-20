package design.prateek.parking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    String id;
    String name;
    float avgRating;
    long numTrips;

    List<Driver> blockedDrivers;

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public void setNumTrips(long numTrips) {
        this.numTrips = numTrips;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public long getNumTrips() {
        return numTrips;
    }

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        numTrips = 0;
        avgRating = 0;

        blockedDrivers = new ArrayList<Driver>();
    }

    @Override
    public String toString() {
        return name + " " + avgRating;
    }
}