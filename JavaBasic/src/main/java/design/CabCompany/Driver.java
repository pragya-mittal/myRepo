package design.CabCompany;

public class Driver implements Comparable<Driver>{
    int driverId;
    int rating;
    int totalTrips;

    public Driver(int driverId, int rating) {
        this.driverId = driverId;
        this.rating = rating;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setTotalTrips(int totalTrips) {
        this.totalTrips = totalTrips;
    }

    public int getTotalTrips() {
        return totalTrips;
    }

    public int compareTo(Driver d) {
        if(this.rating<d.rating)
            return -1;
        else
            return 1;
    }
}
