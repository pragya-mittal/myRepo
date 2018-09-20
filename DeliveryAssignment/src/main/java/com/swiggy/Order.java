package com.swiggy;

public class Order extends SwiggyEntity {
    public Order(String id, Location location, String timestamp, Status status) {
        super(id, location, timestamp, status);
    }
}
