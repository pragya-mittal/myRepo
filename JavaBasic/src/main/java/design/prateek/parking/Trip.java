package design.prateek.parking;

import java.io.Serializable;

public class Trip implements Serializable {
    Driver driver;
    Customer customer;
    float custRating;
    float driverRating;
    long duration;

    public Driver getDriver() {
        return driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public float getCustRating() {
        return custRating;
    }

    public float getDriverRating() {
        return driverRating;
    }

    public long getDuration() {
        return duration;
    }

    public Trip(Driver driver, Customer customer, float driverRating, float customerRating) {
        this.driver = driver;
        this.customer = customer;
        this.custRating = customerRating;
        this.driverRating = driverRating;
    }
}