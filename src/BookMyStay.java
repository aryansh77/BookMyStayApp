// Version 2.1 - Main Class Renamed to BookMyStay

abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    // Getters (Encapsulation)
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Display method
    public void displayRoomDetails() {
        System.out.println("Room Type      : " + roomType);
        System.out.println("Beds           : " + numberOfBeds);
        System.out.println("Price/Night    : ₹" + pricePerNight);
    }
}

// Version 2.0 - New Class
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }
}

// Version 2.0 - New Class
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }
}

// Version 2.0 - New Class
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        // Polymorphism
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static Availability
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Output
        System.out.println("===== HOTEL ROOM DETAILS =====\n");

        System.out.println(">> Single Room");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailability);
        System.out.println();

        System.out.println(">> Double Room");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailability);
        System.out.println();

        System.out.println(">> Suite Room");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
        System.out.println();

        System.out.println("===== END OF LIST =====");
    }
}