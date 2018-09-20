package design.parkinglot.pojo;

import design.parkinglot.Vehicle;

public class Car extends Vehicle {
    public Car(Color color, String licence) {
        super(color, licence);
    }

    @Override
    public boolean canFitInSlot(Slot slot) {
        Size slotSize = slot.size;
        if(slotSize.equals(Size.MEDIUM) || slotSize.equals(Size.LARGE) || slotSize.equals(Size.XLARGE))
            return true;
        return false;
    }
}
