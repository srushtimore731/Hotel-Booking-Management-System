import java.util.HashMap;

abstract class Room {

    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 100);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 180);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 300);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class RoomSearchService {

    void searchRooms(Room[] rooms, RoomInventory inventory) {

        System.out.println("\nAvailable Rooms:\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.type);

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class UC4bookmystay {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Hotel Booking System v4.1");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService searchService = new RoomSearchService();

        searchService.searchRooms(rooms, inventory);
    }
}