import java.util.*;

// Reservation Class
class Reservation {
    String reservationId;
    String customerName;
    String roomType;
    String roomId;

    public Reservation(String reservationId, String customerName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return reservationId + " | " + customerName + " | " + roomType + " | " + roomId;
    }
}

// Main Class
public class BookMyStay {

    // Inventory
    static Map<String, Integer> inventory = new HashMap<>();

    // Active reservations
    static Map<String, Reservation> reservations = new HashMap<>();

    // Stack for rollback (LIFO)
    static Stack<String> rollbackStack = new Stack<>();

    // Add reservation (simulate confirmed booking)
    public static void addReservation(Reservation r) {
        reservations.put(r.reservationId, r);
    }

    // Cancel reservation
    public static void cancelReservation(String reservationId) {

        // Validation
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation FAILED: Reservation not found -> " + reservationId);
            return;
        }

        Reservation r = reservations.get(reservationId);

        // Step 1: Track rollback
        rollbackStack.push(r.roomId);

        // Step 2: Restore inventory
        inventory.put(r.roomType, inventory.get(r.roomType) + 1);

        // Step 3: Remove reservation
        reservations.remove(reservationId);

        System.out.println("Cancellation SUCCESS: " + reservationId);
        System.out.println("Released Room ID: " + r.roomId);
    }

    // Display inventory
    public static void showInventory() {
        System.out.println("\nCurrent Inventory: " + inventory);
    }

    // Display rollback stack
    public static void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Deluxe", 1);
        inventory.put("Standard", 2);

        // Simulate confirmed bookings
        addReservation(new Reservation("RES101", "Aryan", "Deluxe", "DEL_001"));
        addReservation(new Reservation("RES102", "Rahul", "Standard", "STD_002"));

        // Perform cancellations
        cancelReservation("RES101");   // valid
        cancelReservation("RES999");   // invalid

        // Show results
        showInventory();
        showRollbackStack();
    }
}