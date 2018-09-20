package design;


import java.util.HashMap;
import java.util.Map;

public class CarParking {
    private static final int EMPTY_SLOT = -1;
    private Map<Integer, Integer> currentCarsToSlotsMapping;


    // currentSlotOccupancy[i] is Car Id parked at slot i
    public void shuffleCars(int[] currentSlotOccupancy, int[] desiredSlotOccupancy) {
        if (!checkArgs(currentSlotOccupancy, desiredSlotOccupancy)) {
            throw new IllegalArgumentException("Invalid given and desired occupancy");
        }

        currentCarsToSlotsMapping = getCarsToSlotsMapping(currentSlotOccupancy);
        int currentIndex = 0;
        while (currentIndex < currentSlotOccupancy.length) {
            if (!isIndexCorrectlyParked(currentIndex, currentSlotOccupancy, desiredSlotOccupancy)) {
                swap(currentIndex, currentCarsToSlotsMapping.get(desiredSlotOccupancy[currentIndex]),
                        currentSlotOccupancy);
            }
            currentIndex++;
        }
    }

    private boolean isIndexCorrectlyParked(int index, int[] currentSlotOccupancy, int[] desiredSlotOccupancy) {
        if (currentSlotOccupancy[index] == desiredSlotOccupancy[index]) {
            return true;
        }
        return false;
    }

    private void swap(int firstIndex, int secondIndex, int[] currentSlotOccupancy) {
        if (currentCarsToSlotsMapping.get(EMPTY_SLOT) == firstIndex) {
            parkToEmptySlot(secondIndex, currentSlotOccupancy);
            return;
        }
        if (currentCarsToSlotsMapping.get(EMPTY_SLOT) == secondIndex) {
            parkToEmptySlot(firstIndex, currentSlotOccupancy);
            return;
        }
        firstIndex = parkToEmptySlot(firstIndex, currentSlotOccupancy); //  Now Car A will be parked at empty slot index.
        parkToEmptySlot(secondIndex, currentSlotOccupancy); //Now park B to original position of car A, which is empty now.
        parkToEmptySlot(firstIndex, currentSlotOccupancy); // Now Move Car A to original position of B which is empty now.
    }


    // Park car at index i to empty slot and update car to slot mapping and return current position of the car parked.
    private int parkToEmptySlot(int index, int[] currentSlotOccupancy) {
        int emptySlotIndex = currentCarsToSlotsMapping.get(EMPTY_SLOT);
        currentSlotOccupancy[emptySlotIndex] = currentSlotOccupancy[index];
        currentSlotOccupancy[index] = EMPTY_SLOT;
        currentCarsToSlotsMapping.put(currentSlotOccupancy[emptySlotIndex], emptySlotIndex);
        currentCarsToSlotsMapping.put(currentSlotOccupancy[index], index);
        return emptySlotIndex;
    }

    private Map<Integer, Integer> getCarsToSlotsMapping(int[] currentSlotsOccupancy) {
        Map<Integer, Integer> carToSlotMapping = new HashMap<Integer, Integer>();
        for (int index = 0; index < currentSlotsOccupancy.length; index++) {
            carToSlotMapping.put(currentSlotsOccupancy[index], index);
        }
        return carToSlotMapping;
    }

    private boolean checkArgs(int currentPositions[], int[] desiredPositions) {
        if (currentPositions == null || desiredPositions == null ||
                currentPositions.length != desiredPositions.length) {
            return false;
        }
        if (containsEmptySlot(currentPositions) && containsEmptySlot(desiredPositions)) {
            return true;
        }
        return false;
    }

    private boolean containsEmptySlot(int[] parkingSlots) {
        for (int i = 0; i < parkingSlots.length; i++) {
            if (parkingSlots[i] == EMPTY_SLOT) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] currentSlots = {1, 2, 3, 4, -1};
        int[] desiredSlots = {3, 4, 1, 2, -1};
        CarParking carParking = new CarParking();
        carParking.shuffleCars(currentSlots, desiredSlots);

        for (int i = 0; i < currentSlots.length; i++) {
            System.out.print(currentSlots[i] + "  ");
        }

    }
}