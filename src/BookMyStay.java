// Version 3.0 - RoomInventory Class (New Class)
import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add or initialize room type
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Cannot reduce below zero for " + roomType);
        } else {
            inventory.put(roomType, updated);
        }
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("===== CURRENT ROOM INVENTORY =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("==================================");
    }
}


// Version 3.1 - Main Class Renamed to BookMyStay
public class BookMyStay {

    public static void main(String[] args) {

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Register Room Types
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 2);

        // Display Initial Inventory
        inventory.displayInventory();

        // Simulate Updates
        System.out.println("\nUpdating Inventory...\n");

        // Booking a Single Room
        inventory.updateAvailability("Single Room", -1);

        // Adding a Double Room
        inventory.updateAvailability("Double Room", 1);

        // Invalid case
        inventory.updateAvailability("Suite Room", -5);

        // Display Updated Inventory
        inventory.displayInventory();
    }
}