package car_components;


public interface Sensor {

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
