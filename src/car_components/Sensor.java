package car_components;


public interface Sensor {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 200;

    public static boolean isValid(int value) {
        return value >= MIN_VALUE && value <= MAX_VALUE;
    }

    public static final int STREET_SIZE = 500;

    /**
     * Name: measureDistance()
     * Input: Integer of car's location to check if empty.
     * Output: Integer in the range 0 to 200, representing if the car's current location is available for parking.
     * Pre-condition: Car object is instantiated.
     * Post-condition: Car's current location status has been returned.
     *
     * Test cases:
     * See report.
     */

    int measureDistance(int location);
}
