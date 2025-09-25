package ridesharingrequest;

import java.util.Scanner;

class RideRequest {
    private int rideId;
    private String passengerName;
    private String pickupLocation;
    private String dropoffLocation;
    private double fare;

    public RideRequest(int rideId, String passengerName, String pickupLocation, String dropoffLocation, double fare) {
        this.rideId = rideId;
        this.passengerName = passengerName;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.fare = fare;
    }

    public int getRideId() { return rideId; }
    public String getPassengerName() { return passengerName; }
    public String getPickupLocation() { return pickupLocation; }
    public String getDropoffLocation() { return dropoffLocation; }
    public double getFare() { return fare; }

    public void setPassengerName(String name) { this.passengerName = name; }
    public void setPickupLocation(String location) { this.pickupLocation = location; }
    public void setDropoffLocation(String location) { this.dropoffLocation = location; }
    public void setFare(double fare) { this.fare = fare; }

    public void display() {
        System.out.printf("Ride ID: %d | Passenger: %s | Pickup: %s | Dropoff: %s | Fare: R%.2f%n",
                rideId, passengerName, pickupLocation, dropoffLocation, fare);
    }
}

class Node {
    RideRequest ride;
    Node next;

    public Node(RideRequest ride) {
        this.ride = ride;
        this.next = null;
    }
}

class RideList {
    Node head;

    public void insertAtEnd(RideRequest ride) {
        Node newNode = new Node(ride);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
    }

    public void deleteById(int rideId) {
        Node temp = head, prev = null;
        while (temp != null) {
            if (temp.ride.getRideId() == rideId) {
                if (prev == null)
                    head = temp.next;
                else
                    prev.next = temp.next;
                System.out.println("✅ Ride deleted.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("❌ Ride not found.");
    }

    public void searchById(int rideId) {
        Node temp = head;
        while (temp != null) {
            if (temp.ride.getRideId() == rideId) {
                temp.ride.display();
                return;
            }
            temp = temp.next;
        }
        System.out.println("❌ Ride not found.");
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("📭 No rides available.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            temp.ride.display();
            temp = temp.next;
        }
    }

    public RideRequest[] toArray() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        RideRequest[] arr = new RideRequest[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            arr[i] = temp.ride;
            temp = temp.next;
        }
        return arr;
    }
}

public class RideSharingManager {
    public static void sortByFare(RideList list) {
        RideRequest[] rides = list.toArray();
        for (int i = 1; i < rides.length; i++) {
            RideRequest key = rides[i];
            int j = i - 1;
            while (j >= 0 && rides[j].getFare() > key.getFare()) {
                rides[j + 1] = rides[j];
                j--;
            }
            rides[j + 1] = key;
        }

        for (RideRequest ride : rides)
            ride.display();
    }

    public static void printReverse(Node node) {
        if (node == null) return;
        printReverse(node.next);
        node.ride.display();
    }

    public static double totalFare(Node node) {
        if (node == null) return 0;
        return node.ride.getFare() + totalFare(node.next);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RideList list = new RideList();

        while (true) {
            System.out.println("\n--- Ride Sharing Booking Manager ---");
            System.out.println("1. Add Ride Request (Insert at End)");
            System.out.println("2. Delete Ride by ID");
            System.out.println("3. Search Ride by ID");
            System.out.println("4. Display All Rides");
            System.out.println("5. Sort Rides by Fare (Insertion Sort)");
            System.out.println("6. Print Rides in Reverse (Recursion)");
            System.out.println("7. Calculate Total Fare (Recursion)");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Ride ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Pickup Location: ");
                    String pickup = sc.nextLine();
                    System.out.print("Enter Dropoff Location: ");
                    String dropoff = sc.nextLine();
                    System.out.print("Enter Fare: ");
                    double fare = sc.nextDouble();
                    RideRequest ride = new RideRequest(id, name, pickup, dropoff, fare);
                    list.insertAtEnd(ride);
                    break;
                case 2:
                    System.out.print("Enter Ride ID to delete: ");
                    list.deleteById(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Ride ID to search: ");
                    list.searchById(sc.nextInt());
                    break;
                case 4:
                    list.displayAll();
                    break;
                case 5:
                    System.out.println("📊 Sorted Rides by Fare:");
                    sortByFare(list);
                    break;
                case 6:
                    System.out.println("🔁 Rides in Reverse Order:");
                    printReverse(list.head);
                    break;
                case 7:
                    System.out.printf("💰 Total Fare: R%.2f%n", totalFare(list.head));
                    break;
                case 8:
                    System.out.println("👋 Exiting Ride Sharing Booking Manager.");
                    return;
                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }
    }
}
