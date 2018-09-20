package com.swiggy;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class SwiggyEntity {
    String id;
    Location location;
    String timeStamp;
    Status status;

    public SwiggyEntity(String id, Location location, String timestamp, Status status) {
        this.id = id;
        this.location = location;
        this.timeStamp = timestamp;
//        timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(String timestamp) {
        this.timeStamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
