package design.parkinglot;

import design.parkinglot.pojo.*;

import java.util.*;

public class ParkingLot {
    List<Slot> slots = new ArrayList<>();
    int totalSlots;
    Map <Slot, Vehicle> slotVehicleMap;
    Map <String, List<Slot>> sizetoFreeSlotMap;

    public ParkingLot(int floors, int totalSlots) {
        this.totalSlots = totalSlots;
        slotVehicleMap = new HashMap<Slot, Vehicle>();
        sizetoFreeSlotMap = new HashMap<String, List<Slot>>();
        createParkingLot(totalSlots, sizetoFreeSlotMap);
    }

    private void createParkingLot(int totalSlots, Map<String, List<Slot>> sizetoFreeSlotMap) {
        for (int i=0;i<totalSlots;i++) {
            Random random = new Random();
            Slot slot = new Slot(i, 1, Size.values()[random.nextInt(Size.values().length)]);
            slotVehicleMap.put(slot, null);
            addToFreeSlots(slot, sizetoFreeSlotMap);
        }
    }

    private void addToFreeSlots(Slot slot, Map<String, List<Slot>> sizetoFreeSlotMap) {
        List<Slot> slotList;
        if (sizetoFreeSlotMap.get(slot).equals(null)) {
            slotList = new ArrayList<Slot>();
            slotList.add(slot);
            sizetoFreeSlotMap.put(slot.getSize().name(), slotList);
        } else {
            slotList = sizetoFreeSlotMap.get(slot.getSize().name());
            slotList.add(slot);
            sizetoFreeSlotMap.put(slot.getSize().name(), slotList);
        }
    }

    public Slot getFreeSlots(Size size) {
        return sizetoFreeSlotMap.get(size.name()).get(0);
    }

    public Slot getFreeSlotForVehicle(Vehicle vehicle) throws Exception {
        Slot slot;
        if (vehicle instanceof Bike) {
            return getFreeSlots(Size.SMALL);
        }
        else if (vehicle instanceof Car) {
            return getFreeSlots(Size.MEDIUM);
        }
        else if (vehicle instanceof Truck) {
            return getFreeSlots(Size.LARGE);
        }
        else if (vehicle instanceof Bus) {
            return getFreeSlots(Size.XLARGE);
        }
        return null;
    }

    private void removeFromFreeSlots(Slot slot) {
        List<Slot> slotList = sizetoFreeSlotMap.get(slot.getSize().name());
        slotList.remove(slot);
        if (slotList.size()!=0)
            sizetoFreeSlotMap.put(slot.getSize().name(), slotList);
        else
            sizetoFreeSlotMap.remove(slot.getSize().name());
    }

    public Slot park(Vehicle vehicle) throws Exception {
        Slot slot = getFreeSlotForVehicle(vehicle);
        if (slot.equals(null)) {
            System.out.println("No free slot available");
            return null;
        }

        synchronized (slot) {
            if (!slot.equals(null)) {
                if (slotVehicleMap.get(slot).equals(null)) {
                    slotVehicleMap.put(slot, vehicle);
                    removeFromFreeSlots(slot);
                    return slot;
                }
            }
        }
        return null;
    }

    public void unpark(Slot slot) {
        synchronized (slot) {
            slotVehicleMap.remove(slot);
            addToFreeSlots(slot, sizetoFreeSlotMap);
        }
    }
}
