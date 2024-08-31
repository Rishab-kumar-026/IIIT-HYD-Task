import java.util.Scanner;

 class LiftSystem {
    private static final int MAX_CAPACITY = 4;
    private int currentPeople = 0;
    private int currentFloor = 1;
    private int[] destinations;  // Array to store the destination floor of each person

    public void enterLift(int peopleEntering) {
        if (currentPeople + peopleEntering > MAX_CAPACITY) {
            System.out.println("Warning: Too many people! The maximum capacity is " + MAX_CAPACITY + ".");
        } else {
            currentPeople += peopleEntering;
            System.out.println(peopleEntering + " people entered the lift at floor " + currentFloor);
            destinations = new int[peopleEntering];
        }
    }

    public void inputDestinations() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < currentPeople; i++) {
            System.out.print("Enter the destination floor for person " + (i + 1) + ": ");
            int destination = scanner.nextInt();
            if (destination < 1 || destination > 10) {
                System.out.println("Invalid floor! Please select a floor between 1 and 10.");
                i--;  // Repeat the input for the same person if invalid floor
            } else {
                destinations[i] = destination;
            }
        }
    }

    public void moveLift() {
        // Sort destinations so the lift moves in ascending order of floors
        java.util.Arrays.sort(destinations);

        for (int i = 0; i < destinations.length; ) {
            int destinationFloor = destinations[i];
            System.out.println("Lift is moving from floor " + currentFloor + " to floor " + destinationFloor + "...");
            currentFloor = destinationFloor;
            System.out.println("Lift reached floor " + currentFloor);

            // Count how many people have the same destination
            int count = 1;
            while (i + count < destinations.length && destinations[i + count] == destinationFloor) {
                count++;
            }

            exitLift(count);
            i += count;  // Skip to the next unique destination floor
        }
    }

    public void exitLift(int count) {
        if (count == 1) {
            System.out.println("1 person exited the lift at floor " + currentFloor);
        } else {
            System.out.println(count + " people exited the lift at floor " + currentFloor);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LiftSystem lift = new LiftSystem();

        while (true) {
            System.out.println("\n--- Lift System ---");
            System.out.println("Current floor: " + lift.currentFloor);
            System.out.print("Enter the number of people entering the lift (max 4): ");
            int peopleEntering = scanner.nextInt();
            lift.enterLift(peopleEntering);

            if (lift.currentPeople > 0) {
                lift.inputDestinations();
                lift.moveLift();
            }

            // Reset lift for the next set of operations
            lift.currentPeople = 0;
        }
    }
}
