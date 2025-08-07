import java.io.*;
import java.util.*;

class Room {
    int number;
    String category;
    boolean booked;
    String customer;

    Room(int number, String category) {
        this.number = number;
        this.category = category;
        this.booked = false;
        this.customer = "";
    }
}

public class CodeAlpha_HotelReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static String fileName = "bookings.txt";

    public static void main(String[] args) {
        loadRooms();
        while (true) {
            System.out.println("\n--- Hotel Reservation Menu ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> viewBookingDetails();
                case 5 -> { saveRooms(); return; }
            }
        }
    }

    static void loadRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Deluxe"));
        rooms.add(new Room(103, "Suite"));

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (Room r : rooms) {
                    if (r.number == Integer.parseInt(data[0])) {
                        r.booked = Boolean.parseBoolean(data[2]);
                        r.customer = data[3];
                    }
                }
            }
        } catch (IOException ignored) {}
    }

    static void saveRooms() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Room r : rooms) {
                pw.println(r.number + "," + r.category + "," + r.booked + "," + r.customer);
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    static void viewRooms() {
        for (Room r : rooms) {
            System.out.println("Room " + r.number + " (" + r.category + ") - " + (r.booked ? "Booked" : "Available"));
        }
    }

    static void bookRoom() {
        System.out.print("Enter room number to book: ");
        int num = sc.nextInt();
        for (Room r : rooms) {
            if (r.number == num && !r.booked) {
                System.out.print("Enter customer name: ");
                r.customer = sc.next();
                r.booked = true;
                System.out.println("Room booked successfully! Payment simulated.");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void cancelBooking() {
        System.out.print("Enter room number to cancel: ");
        int num = sc.nextInt();
        for (Room r : rooms) {
            if (r.number == num && r.booked) {
                r.booked = false;
                r.customer = "";
                System.out.println("Booking canceled!");
                return;
            }
        }
        System.out.println("Room not booked!");
    }

    static void viewBookingDetails() {
        for (Room r : rooms) {
            if (r.booked) {
                System.out.println("Room " + r.number + " (" + r.category + ") - Booked by " + r.customer);
            }
        }
    }
}
