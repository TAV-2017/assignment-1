/**
 * Created by MTs on 28/01/17.
 *
 */

public class CarImpl implements Car {

    private int location;               // Needed in order to keep track of the car's location. TC 1, 2, 3

    private int empty_meters;           // Needed in order to find empty parking places. TC 5
    private int empty_parking_places;   // Needed in order to keep track of empty parking places. TC 4

    private boolean visited[];          // To allow backtracking, and to prevent registering a parking space twice. TC 5

    private boolean street[];           // Needed to test the entire assignment, no specific Test case.

    private static final int STREET_SIZE = 500; // Since the street should be 500 meters long according to the assignment
                                                // specification.
    public CarImpl() {
        location = 1;                   // Initialize car's location to 1 to accurately represent the car being at the beginning of the street, TC 1
        empty_meters = 0;               // While no empty meters have been noticed, needs to be initialized to 0, TC 6
        empty_parking_places = 0;       // Empty parking places should be 0 before one is discovered, TC 4

        visited = new boolean[STREET_SIZE];     // The visited array keeps track of where the car has been so that the
                                                // parking counter does not increase if the car has moved back and forth.
                                                // TC 5
        /*
        Initializing the street to contain a set number of parking spaces, one every 50 meters, 10 in total.
         */

        street = new boolean[STREET_SIZE];
        for (int i = 0; i < 500; i += 50) {
            int count = i;
            for (int j = 0; j < 5; j++) {
                street[count] = true;
                count++;
            }
        }
    }

    public int[] moveForward() {

        if (!visited[location - 1]) {           // Visited is needed to avoid incrementing empty parking spaces more than
            visited[location - 1] = true;       // once if the car moves back and forth. TC 5

            if (location == 1 && isEmpty() == 200) {    // If a parking space is at the very beginning of the street,
                empty_meters++;                         // this space was not checked without this if-statement. TC 6
            }

            if (location < STREET_SIZE) {               // Avoid going beyond the street border, TC 3
                location++;

                if (isEmpty() == 200) {         // In order to find empty 5-meter stretches, TC 6
                    empty_meters++;             // TC 6

                    if (empty_meters == 5) {    // TC 6
                        empty_parking_places++; // TC 6
                        empty_meters = 0;       // TC 6
                    }
                } else {
                    empty_meters = 0;           // For example, if an empty strech is located that is just 3 meters long,
                }                               // empty meters needs to be reset, TC 5, 6
            }
        // If the space has been visited, the car still moves forward by does not register parking spaces.
        } else if (location < STREET_SIZE) {    // Avoid going beyond street border, TC 3
            location++;                         // Increment location to accurately represent car's location, TC 2
        }


        // Return the location and current number of noticed parking spaces (5 meter stretches).
        return new int[] {location, empty_parking_places};  // To return the information that is desired by the specification, TC 6
    }

    public int[] moveBackward() {

        if (location > 1) {     // To avoid the car going beyond the street border, TC 8
            location--;         // To make sure that the car moves backwards when moveBackwards() is called, TC 9, 5, 10
        }

        return new int[] {location, empty_parking_places};      // Needed to satisfy the requirement to return an integer
    }                                                           // array of the car's location and number of detected parking places. TC 11

    public int isEmpty() {
        int sensor_1_total = 0;
        int sensor_2_total = 0;

        int sensor_1_average;
        int sensor_2_average;

        int average;

        /*
        Gets sensor values five times, as per the assignment specification.
         */
        for (int i = 0; i < 5; i++) {
            sensor_1_total += sensor_1();
            sensor_2_total += sensor_2();
        }

        /*
        Gets the average values for the sensors, as per the assignment specification.
         */
        sensor_1_average = sensor_1_total / 5;
        sensor_2_average = sensor_2_total / 5;
        average = sensor_1_average + sensor_2_average;

        // By returning just one of the sensor's value, it is ensured that the returned integer is within the range of 0 -> 200, TC 7, 6, 5
        if (average == 400 || average == 0) {
            return sensor_1_average;
        } else if (sensor_1_average == 200 || sensor_1_average == 0) {
            return sensor_1_average;
        } else {
            return sensor_2_average;
        }
    }

    @Override
    public boolean park() {
        return false;
    }

    @Override
    public boolean unPark() {
        return false;
    }

    @Override
    public int[] whereIs() {
        return new int[0];
    }

    private int sensor_1() {

        // The street array of booleans is initialized in the car's constructor.
        if (street[location - 1]) {
            return 200;
        } else {
            return 0;
        }
    }

    private int sensor_2() {

        // The street array of booleans is initialized in the car's constructor.
        if (street[location - 1]) {
            return 200;
        } else {
            return 0;
        }
    }

    // Necessary for testing the car's location, TC 1
    public int getLocation() {
        return location;
    }

    // Necessary for testing the car's number of found parking spaces, TC 4
    public int getEmpty_parking_places() {
        return empty_parking_places;
    }
}
