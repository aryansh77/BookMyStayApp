// Version 5.1 - Clean & Structured Implementation

import java.util.LinkedList;
import java.util.Queue;

// -------------------- Reservation Class --------------------
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type  : " + roomType);
        System.out.println("---------------------------");
    }
}

// -------------------- Booking Queue Class --------------------
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request (FIFO)
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request added for: " + reservation.getGuestName());
    }

    // Display all requests (without removing)
    public void displayAllRequests() {
        System.out.println("\n===== BOOKING REQUEST QUEUE =====");

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests available.");
        } else {
            for (Reservation r : requestQueue) {
                r.displayReservation();
            }
        }

        System.out.println("=================================");
    }
}

// -------------------- Main Class --------------------
public class BookMyStay {

    public static void main(String[] args) {

        // Initialize Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue (FIFO order maintained)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display all queued requests
        bookingQueue.displayAllRequests();

        // NOTE:
        // No booking allocation or inventory updates are done here.
        // Only request intake is handled (as per Use Case 5).
    }
}