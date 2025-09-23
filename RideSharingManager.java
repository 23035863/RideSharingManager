import java.util.Scanner;

public class RideSharingManager {
    public static void insertionSort(RideRequest[] arr) {
        for (int i = 1; i < arr.length; i++) {
            RideRequest key = arr[i];
            int j = i - 1;
            while (j >= 0 ) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RideList rideList = new RideList();

        while (true) {
            System.out.println("\n--- Ride Sharing Booking Manager ---");
            System.out.println("1. Add Ride Request");
            System.out.println("2. Delete Ride by ID");
            System.out.println("3. Search Ride by ID");
            System.out.println("4. Display All Rides");
            System.out.println("5. Sort Rides by Fare");
            System.out.println("6. Print Rides in Reverse");
            System.out.println("7. Calculate Total Fare");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Ride ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Passenger Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Pickup Location: ");
                    String pickup = scanner.nextLine();
                    System.out.print("Dropoff Location: ");
                    String dropoff = scanner.nextLine();
                    System.out.print("Fare: ");
                    float fare = scanner.nextFloat();
                    rideList.insertAtEnd(new RideRequest(id, name, pickup, dropoff, fare));
                    System.out.println("Ride added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Ride ID to delete: ");
                    int delId = scanner.nextInt();
                    System.out.println(rideList.deleteById(delId) ?
                            "Ride deleted." : "Ride not found.");
                    break;

                case 3:
                    System.out.print("Enter Ride ID to search: ");
                    int searchId = scanner.nextInt();
                    RideRequest ride = rideList.searchById(searchId);
                    if (ride != null) ride.display();
                    else System.out.println("Ride not found.");
                    break;

                case 4:
                    rideList.displayAll();
                    break;

                case 5:
                    RideRequest[] arr = rideList.toArray();
                    insertionSort(arr);
                    System.out.println("Sorted Rides by Fare:");
                    for (RideRequest r : arr) r.display();
                    break;

                case 6:
                    System.out.println("Rides in Reverse Order:");
                    rideList.printReverse();
                    break;

                case 7:
                    System.out.printf("Total Fare: %.2f%n", rideList.totalFare());
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}