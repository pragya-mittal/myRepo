package design.parkinglot;

import design.parkinglot.pojo.Color;
import design.parkinglot.pojo.Slot;

public abstract class Vehicle {
    Color color;
    String licence;

    public Vehicle(Color color, String licence) {
        this.color = color;
        this.licence = licence;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public abstract boolean canFitInSlot(Slot slot);


}
