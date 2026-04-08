// Version 4.1 - Final Structured Code

import java.util.HashMap;
import java.util.Map;

// Version 4.0 - Room (Abstract Class)
abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void displayDetails() {
        System.out.println("Room Type   : " + roomType);
        System.out.println("Beds        : " + numberOfBeds);
        System.out.println("Price/Night : ₹" + pricePerNight);
    }
}

// Version 4.0 - Concrete Room Classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }
}

// Version 4.0 - Inventory (Read-only for search)
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // Read-only method
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// Version 4.0 - Search Service
class RoomSearchService {

    public void searchAvailableRooms(Room[] rooms, RoomInventory inventory) {

        System.out.println("===== AVAILABLE ROOMS =====\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());

            // Defensive check
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println();
            }
        }

        System.out.println("===== END OF SEARCH =====");
    }
}

// Version 4.1 - Main Class
public class BookMyStay {

    public static void main(String[] args) {

        // Domain Objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = { single, doubleRoom, suite };

        // Inventory (State)
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); // filtered out
        inventory.addRoomType("Suite Room", 2);

        // Search Service (Read-only)
        RoomSearchService searchService = new RoomSearchService();

        // Execute Search
        searchService.searchAvailableRooms(rooms, inventory);
    }
}