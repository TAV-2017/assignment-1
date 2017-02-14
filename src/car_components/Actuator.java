package car_components;


public interface Actuator {

    /**
     * Name: forward()
     * Input: void
     * Output: Integer representing the car's current position, from 1 to 500.
     * Pre-condition: Car object is instantiated.
     * Post-condition: Car's location is incremented by one, or car is in the same place if it was at the end of the
     * street.
     *
     * Test cases:
     * See report.
     */

    int forward();

    /**
     * Name: backward()
     * Input: void
     * Output: Integer representing the car's current position, from 1 to 500.
     * Pre-condition: Car object is instantiated.
     * Post-condition: Car's location is decremented by one, or car is in the same place if it was at the beginning of
     * the street.
     *
     * Test cases:
     * See report.
     */

    int backward();

    /**
     * Name: getLocation()
     * Input: void
     * Output: Integer representing the car's current location.
     * Pre-condition: Car object is instantiated.
     * Post-condition: The car's location has been returned.
     *
     * Test cases:
     * None, this is a supporting method.
     */

    int getLocation();

}
