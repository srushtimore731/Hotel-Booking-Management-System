import java.util.*;

class BookingReservation {
    String guestName;
    String roomType;

    BookingReservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class BookingService {

    private Set<String> allocatedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();
    private int roomCounter = 1;

    void processBookings(Queue<BookingReservation> queue, RoomInventory inventory) {

        while (!queue.isEmpty()) {

            BookingReservation r = queue.poll();
            int available = inventory.getAvailability(r.roomType);

            if (available > 0) {

                String prefix = r.roomType.substring(0, 2).toUpperCase();
                String roomId = prefix + roomCounter++;

                while (allocatedRoomIds.contains(roomId)) {
                    roomId = prefix + roomCounter++;
                }

                allocatedRoomIds.add(roomId);

                roomAllocations.putIfAbsent(r.roomType, new HashSet<>());
                roomAllocations.get(r.roomType).add(roomId);

                inventory.decrement(r.roomType);

                System.out.println("Reservation Confirmed: " + r.guestName +
                        " | Room Type: " + r.roomType +
                        " | Room ID: " + roomId);

            } else {
                System.out.println("Reservation Failed: " + r.guestName +
                        " | No available " + r.roomType);
            }
        }
    }
}

public class UC6bookmystay {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Hotel Booking System v6.1");

        Queue<BookingReservation> requestQueue = new LinkedList<>();

        requestQueue.add(new BookingReservation("Alice", "Single Room"));
        requestQueue.add(new BookingReservation("Bob", "Double Room"));
        requestQueue.add(new BookingReservation("Charlie", "Suite Room"));
        requestQueue.add(new BookingReservation("David", "Single Room"));
        requestQueue.add(new BookingReservation("Eva", "Suite Room"));

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService();

        bookingService.processBookings(requestQueue, inventory);
    }
}