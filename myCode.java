import java.util.Scanner;
import java.util.Arrays;

class Lift {
    int lift_capacity = 4;
    int currentFloor = 1;
    int currentPeople = 0;
    int[] destinations;

    // Method to handle people entering the lift
    public void entering_lift(int people_entered_lift) {
        if (currentPeople + people_entered_lift > lift_capacity) {
            System.out.println("Too many people have entered the lift. The MAX capacity of the lift is: " + lift_capacity);
        } else {
            currentPeople += people_entered_lift;
            destinations = new int[currentPeople]; // initialize the destinations array based on people entered
            System.out.println(currentPeople + " persons entered the lift at floor " + currentFloor);
        }
    }

    // Method to input destinations for each person in the lift
    public void input_destinations() {
        Scanner obj = new Scanner(System.in);
        for (int i = 0; i < currentPeople; i++) {
            System.out.println("Enter the destination floor of Person " + (i + 1) + ":");
            int destination = obj.nextInt();

            if (destination < 1 || destination > 10) {
                System.out.println("Please enter a valid destination floor (1-10).");
                i--;
            } else {
                destinations[i] = destination;
            }
        }
    }

    // Method to move the lift and handle people exiting
    public void move_lift() {
        // Sort destinations so the lift moves in ascending order of floors
        Arrays.sort(destinations);

        for (int i = 0; i < destinations.length; ) {
            int destination_Floor = destinations[i];
            System.out.println("Lift is moving from floor " + currentFloor + " to floor " + destination_Floor + "...");
            currentFloor = destination_Floor;
            System.out.println("Lift reached floor " + currentFloor);

            // Count how many people have the same destination
            int count = 1;
            while (i + count < destinations.length && destinations[i + count] == destination_Floor) {
                count++;
            }

            exit_lift(count);
            i += count;  // Skip to the next unique destination floor
        }
    }

    public void exit_lift(int count) {
        if (count == 1) {
            System.out.println("1 person exited the lift at floor " + currentFloor);
        } else {
            System.out.println(count + " people exited the lift at floor " + currentFloor);
        }
    }

    // Main method to run the lift simulation
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Lift lift = new Lift();

        while (true) {
            System.out.println("\nLift System");
            System.out.println("Current floor of the lift: " + lift.currentFloor);
            System.out.println("Enter the number of people entering the lift: ");
            int people_entered_lift = obj.nextInt();
            lift.entering_lift(people_entered_lift);

            if (lift.currentPeople > 0) {
                lift.input_destinations();
                lift.move_lift();
            }

            lift.currentPeople = 0; // reset the lift for the next group of people
        }
    }
}
