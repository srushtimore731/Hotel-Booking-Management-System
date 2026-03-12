import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        }
    }

    void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}

public class UC3bookmystay {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Hotel Booking System v3.1");

        RoomInventory inventory = new RoomInventory();

        System.out.println("\n--- Current Room Inventory ---");
        inventory.displayInventory();

        System.out.println("\nUpdating Suite Room availability...\n");
        inventory.updateAvailability("Suite Room", 1);

        System.out.println("--- Updated Room Inventory ---");
        inventory.displayInventory();
    }
}