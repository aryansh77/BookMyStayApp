import java.util.*;

// Booking Request
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

    // Shared Queue (FIFO)
    static Queue<BookingRequest> requestQueue = new LinkedList<>();

    // Shared Inventory
    static Map<String, Integer> inventory = new HashMap<>();

    // Lock object for synchronization
    static final Object lock = new Object();

    // Booking Processor (Thread)
    static class BookingProcessor extends Thread {

        public void run() {

            while (true) {
                BookingRequest request;

                // Synchronize queue access
                synchronized (lock) {
                    if (requestQueue.isEmpty()) {
                        break;
                    }
                    request = requestQueue.poll();
                }

                processBooking(request);
            }
        }
    }

    // Critical Section: Booking Logic
    public static void processBooking(BookingRequest request) {

        synchronized (lock) {

            System.out.println(Thread.currentThread().getName() +
                    " processing " + request.customerName);

            int available = inventory.getOrDefault(request.roomType, 0);

            if (available > 0) {

                // Simulate delay (to expose race condition if not synchronized)
                try { Thread.sleep(100); } catch (Exception e) {}

                inventory.put(request.roomType, available - 1);

                System.out.println("Booking SUCCESS for " + request.customerName +
                        " | Room: " + request.roomType);

            } else {
                System.out.println("Booking FAILED for " + request.customerName +
                        " | No rooms available");
            }
        }
    }

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Deluxe", 2);

        // Add multiple requests (simulate concurrency)
        requestQueue.add(new BookingRequest("Aryan", "Deluxe"));
        requestQueue.add(new BookingRequest("Rahul", "Deluxe"));
        requestQueue.add(new BookingRequest("Ankit", "Deluxe"));
        requestQueue.add(new BookingRequest("Vikram", "Deluxe"));

        // Create multiple threads
        Thread t1 = new BookingProcessor();
        Thread t2 = new BookingProcessor();
        Thread t3 = new BookingProcessor();

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {}

        // Final inventory
        System.out.println("\nFinal Inventory: " + inventory);
    }
}