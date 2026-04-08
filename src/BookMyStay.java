import java.io.*;
import java.util.*;

// Reservation class (must be Serializable)
class Reservation implements Serializable {
    String reservationId;
    String customerName;
    String roomType;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return reservationId + " | " + customerName + " | " + roomType;
    }
}

// Wrapper class for storing system state
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Main Class
public class BookMyStay {

    static Map<String, Integer> inventory = new HashMap<>();
    static List<Reservation> bookingHistory = new ArrayList<>();

    static final String FILE_NAME = "bookmystay.dat";

    // Save state to file
    public static void saveState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            SystemState state = new SystemState(inventory, bookingHistory);
            oos.writeObject(state);

            System.out.println("System state SAVED successfully.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state from file
    public static void loadState() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No previous data found. Starting fresh.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            SystemState state = (SystemState) ois.readObject();
            inventory = state.inventory;
            bookingHistory = state.bookings;

            System.out.println("System state LOADED successfully.");

        } catch (Exception e) {
            System.out.println("Error loading state. Starting with empty data.");
            inventory = new HashMap<>();
            bookingHistory = new ArrayList<>();
        }
    }

    public static void main(String[] args) {

        // Step 1: Load previous state
        loadState();

        // If empty, initialize
        if (inventory.isEmpty()) {
            inventory.put("Deluxe", 2);
            inventory.put("Standard", 1);
        }

        // Simulate booking
        bookingHistory.add(new Reservation("RES201", "Aryan", "Deluxe"));
        inventory.put("Deluxe", inventory.get("Deluxe") - 1);

        // Show current state
        System.out.println("\nCurrent Inventory: " + inventory);
        System.out.println("Booking History: " + bookingHistory);

        // Step 2: Save state before exit
        saveState();
    }
}