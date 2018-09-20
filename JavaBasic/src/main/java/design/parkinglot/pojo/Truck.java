package design.parkinglot.pojo;

import design.parkinglot.Vehicle;

public class Truck extends Vehicle {
    public Truck(Color color, String licence) {
        super(color, licence);
    }

    @Override
    public boolean canFitInSlot(Slot slot) {
        Size slotSize = slot.size;
        if(slotSize.equals(Size.LARGE) || slotSize.equals(Size.XLARGE))
            return true;
        return false;
    }

}
