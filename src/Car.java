/**
 * Created by MTs on 28/01/17.
 *
 */

public class Car {

    private int location;               // Current location (measured in meters) of the car.

    private boolean parked;

    private int empty_meters;           // Current number of empty meters encountered.
    private int empty_parking_places;   // Current number of empty 5-meter stretches encountered.

    private boolean visited[];

    private boolean street[];           // To test an actual street. Contains true for "this current **METER** you're on
                                        // is available for parking." false means there is a car there.

    private static final int STREET_SIZE = 500;

    public Car() {
        location = 1;
        empty_meters = 0;
        empty_parking_places = 0;

        visited = new boolean[STREET_SIZE];

        /*
        Initializing the street to contain a set number of parking spaces, one every 50 meters.
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

        if (location == 1 && isEmpty() == 200) {
            empty_meters++;
        }

        if (location < 500) {
            location++;

            if (isEmpty() == 200) {
                empty_meters++;

                if (empty_meters == 5) {
                    empty_parking_places++;
                    empty_meters = 0;
                }
            } else {
                empty_meters = 0;
            }
        } else {

        }


        // Return the location and current number of noticed parking spaces (5 meter stretches).
        return new int[] {location, empty_parking_places};
    }

    public int[] moveBackwards() {

        if (isEmpty() == 200 && location > 1) {
            empty_meters--;

            if (empty_meters == -5) {
                empty_parking_places--;
                empty_meters = 0;
            }

            location--;
        } else if (location > 1) {
            empty_meters = 0;

            location--;
        } else {
            /* Do nothing. */
        }

        if (location == 1 && isEmpty() == 200 && empty_parking_places != 0) {
            empty_parking_places--;
            empty_meters = 0;
        }

        return new int[] {location, empty_parking_places};
    }

    public int isEmpty() {
        int sensor_1_total = 0;
        int sensor_2_total = 0;

        int sensor_1_average;
        int sensor_2_average;

        int average;

        for (int i = 0; i < 5; i++) {
            sensor_1_total += sensor_1();
            sensor_2_total += sensor_2();
        }

        sensor_1_average = sensor_1_total / 5;
        sensor_2_average = sensor_2_total / 5;
        average = sensor_1_average + sensor_2_average;

        if (average == 400 || average == 0) {
            return sensor_1_average;
        } else if (sensor_1_average == 200 || sensor_1_average == 0) {
            return sensor_1_average;
        } else {
            return sensor_2_average;
        }
    }

    public int sensor_1() {
        if (street[location - 1]) {
            return 200;
        } else {
            return 0;
        }
    }

    public int sensor_2() {
        if (street[location - 1]) {
            return 200;
        } else {
            return 0;
        }
    }

    public void park() {

        if (location < STREET_SIZE) {
            int parking_spaces[];
            while (!parked) {
                parking_spaces = this.moveForward();

                if (parking_spaces[1] != 0){
                    for(int i=parking_spaces[0]; i < 5 ; i++){
                        moveBackwards();
                    }
                } parked = true;
            }
        }
    }

    public int getLocation() {
        return location;
    }

    public boolean getParked() {
        return parked;
    }
}
