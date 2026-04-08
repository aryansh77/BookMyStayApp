import java.util.*;

// Booking Request Class
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Main Class
public class BookMyStay {

    // Queue for booking requests (FIFO)
    static Queue<BookingRequest> requestQueue = new LinkedList<>();

    // Inventory (RoomType -> Count)
    static Map<String, Integer> inventory = new HashMap<>();

    // RoomType -> Set of allocated Room IDs
    static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global set to ensure uniqueness
    static Set<String> allRoomIds = new HashSet<>();

    // Method to generate unique Room ID
    static String generateRoomId(String roomType) {
        String roomId;
        do {
            roomId = roomType.substring(0, 3).toUpperCase() + "_" + UUID.randomUUID().toString().substring(0, 5);
        } while (allRoomIds.contains(roomId)); // ensure uniqueness

        return roomId;
    }

    // Process Booking Requests
    static void processBookings() {
        while (!requestQueue.isEmpty()) {

            BookingRequest request = requestQueue.poll(); // FIFO

            String roomType = request.roomType;

            System.out.println("\nProcessing request for: " + request.customerName);

            // Check availability
            if (inventory.getOrDefault(roomType, 0) > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(roomType);

                // Store in global set
                allRoomIds.add(roomId);

                // Store in room type map
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                allocatedRooms.get(roomType).add(roomId);

                // Update inventory immediately
                inventory.put(roomType, inventory.get(roomType) - 1);

                // Confirm booking
                System.out.println("Booking CONFIRMED");
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);

            } else {
                System.out.println("Booking FAILED - No rooms available for " + roomType);
            }
        }
    }

    public static void main(String[] args) {

        // Initialize Inventory
        inventory.put("Deluxe", 2);
        inventory.put("Standard", 1);

        // Add booking requests
        requestQueue.add(new BookingRequest("Aryan", "Deluxe"));
        requestQueue.add(new BookingRequest("Rahul", "Deluxe"));
        requestQueue.add(new BookingRequest("Ankit", "Standard"));
        requestQueue.add(new BookingRequest("Vikram", "Deluxe")); // should fail

        // Process all bookings
        processBookings();

        // Final State
        System.out.println("\nFinal Allocated Rooms:");
        System.out.println(allocatedRooms);

        System.out.println("\nRemaining Inventory:");
        System.out.println(inventory);
    }
}