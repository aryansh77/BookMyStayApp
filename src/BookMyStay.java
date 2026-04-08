import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator Class
class BookingValidator {

    // Validate room type
    public static void validateRoomType(String roomType, Map<String, Integer> inventory)
            throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }
    }

    // Validate inventory
    public static void validateAvailability(String roomType, Map<String, Integer> inventory)
            throws InvalidBookingException {

        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }
}

// Main Class
public class BookMyStay {

    static Map<String, Integer> inventory = new HashMap<>();

    // Booking method with validation
    public static void bookRoom(String customerName, String roomType) {

        try {
            // Step 1: Validate input
            BookingValidator.validateRoomType(roomType, inventory);
            BookingValidator.validateAvailability(roomType, inventory);

            // Step 2: Process booking
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booking SUCCESS for " + customerName +
                    " | Room Type: " + roomType);

        } catch (InvalidBookingException e) {
            // Graceful failure
            System.out.println("Booking FAILED for " + customerName +
                    " | Reason: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Deluxe", 1);
        inventory.put("Standard", 0);

        // Test cases
        bookRoom("Aryan", "Deluxe");     // valid
        bookRoom("Rahul", "Deluxe");     // no availability
        bookRoom("Ankit", "Suite");      // invalid room type
        bookRoom("Vikram", "Standard");  // zero inventory

        // Final inventory state
        System.out.println("\nFinal Inventory:");
        System.out.println(inventory);
    }
}