package design.CabCompany;


public class Customer implements Comparable<Customer>{
    int customerId;
    int rating;
    int totalTrips;

    public Customer(int customerId, int rating) {
        this.customerId = customerId;
        this.rating = rating;
    }

    public int getCustomerId() {
        return customerId;
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

    public int compareTo(Customer d) {
        if(this.rating<d.rating)
            return -1;
        else
            return 1;
    }
}
