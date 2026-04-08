import java.util.*;

// Reservation Class
class Reservation {
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
        return "ReservationID: " + reservationId +
                ", Name: " + customerName +
                ", RoomType: " + roomType;
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {

    // List to maintain order
    private List<Reservation> history = new ArrayList<>();

    // Add reservation
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    // Display all bookings
    public void showAllBookings(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n--- Booking History ---");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    // Generate summary report
    public void generateSummary(List<Reservation> reservations) {

        System.out.println("\n--- Booking Summary Report ---");

        // Count per room type
        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomCount.put(r.roomType,
                    roomCount.getOrDefault(r.roomType, 0) + 1);
        }

        for (String type : roomCount.keySet()) {
            System.out.println("Room Type: " + type + " | Bookings: " + roomCount.get(type));
        }

        System.out.println("Total Bookings: " + reservations.size());
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        history.addReservation(new Reservation("DEL_101", "Aryan", "Deluxe"));
        history.addReservation(new Reservation("STD_102", "Rahul", "Standard"));
        history.addReservation(new Reservation("DEL_103", "Ankit", "Deluxe"));

        // Admin views all bookings
        reportService.showAllBookings(history.getAllReservations());

        // Admin generates report
        reportService.generateSummary(history.getAllReservations());
    }
}