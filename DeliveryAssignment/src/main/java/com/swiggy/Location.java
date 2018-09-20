package com.swiggy;

public class Location {
    String latitide;
    String longitude;

    public Location(String latitide, String longitude) {
        this.latitide = latitide;
        this.longitude = longitude;
    }

    public String getLatitide() {
        return latitide;
    }

    public void setLatitide(String latitide) {
        this.latitide = latitide;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
