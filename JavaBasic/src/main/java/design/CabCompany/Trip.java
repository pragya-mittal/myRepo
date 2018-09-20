package design.CabCompany;

public class Trip {
    int tripId;
    int driverRating;
    int customerRating;
    Driver driver;
    Customer customer;
    Location location;

    public Trip(int tripId, Driver driver, int driverRating, Customer customer, int customerRating, Location location) {
        this.tripId = tripId;
        this.driver = driver;
        this.driverRating = driverRating;
        this.customer = customer;
        this.customerRating = customerRating;
        this.location = location;

    }

    public int getTripId() {
        return tripId;
    }

    public int getDriverRating() {
        return driverRating;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public Driver getDriver() {
        return driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Location getLocation() {
        return location;
    }
}
