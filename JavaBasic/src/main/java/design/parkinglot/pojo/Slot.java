package design.parkinglot.pojo;

import design.parkinglot.pojo.Size;

public class Slot {
    int id;
    int floor;
    Size size;

    public Slot(int id, int floor, Size size) {
        this.id = id;
        this.floor = floor;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
