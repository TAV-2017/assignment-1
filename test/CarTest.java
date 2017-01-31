import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {

    Car car;

    /**
     * For moveForward there are the following tests:
     * 1. Test where the car starts, should be location 1 for meter 1 of the 500 meter street.
     * 2. Test where the car is located after calling moveForward() once, should be at location 2.
     * 3. Since a parking space exists at location 1-5, after moving forwards to location 5, a parking space should
     *    have been detected.
     * 4. Move the car forwards and try to go past location 500.
     * 5. Check that all 10 parking spaces have been detected at the end of the street.
     * 6. Check that if the car moves backwards and then forwards again, it actually moves forward.
     *
     * @doc
     */

    @Test
    public void testMoveForward() {
        car = new Car();

        Assertions.assertEquals(1, car.getLocation(), "Car did not start at position 1.");

        car.moveForward();

        Assertions.assertEquals(2, car.getLocation(), "Car did not move one step forward.");

        car.moveForward();
        car.moveForward();
        car.moveForward();

        Assertions.assertEquals(1, car.getEmpty_parking_places(), "Car did not find a parking space" +
                " in the beginning of the street.");

        // Move car to the end of the street, and try to go beyond it.
        for (int i = 0; i < 500; i++) {
            car.moveForward();
        }

        Assertions.assertEquals(500, car.getLocation(), "The car did not stop at the end of" +
                " the street.");

        // We expect 10 since we know how many parking spaces we put in.
        Assertions.assertEquals(10, car.getEmpty_parking_places(), "Car did not find the 10 parking" +
                " spaces.");

        for (int i = 0; i < 10; i++) {
            car.moveBackwards();
        }

        for (int i = 0; i < 5; i++) {
            car.moveForward();
        }

        Assertions.assertEquals(495, car.getLocation(), "Car did not move forwards correctly after" +
                " backing up.");
     }

    /**
     * 1. Check that the isEmpty() method returns a number between 0 and 200.
     *
     * @doc
     */

    @Test
    public void testIsEmpty() {
        car = new Car();

        boolean isInRange = car.isEmpty() >= 0 && car.isEmpty() <= 200 ? true : false;

        Assertions.assertEquals(true, isInRange, "Car sensors gave a value outside of 0 -> 200.");
    }

    /**
     * 1. Check that the car does not go past location 1.
     * 2. Move the car forward and back all the way again, assume that the car is back at location 1.
     * 3. Assume that after going to the end of the street and back again that the number of parking spaces is 10.
     * 4. After moving to the end of the street and back again *once more*, found parking spaces should still be 10.
     *
     * @doc
     */

    @Test
    public void testMoveBackwards() {
        car = new Car();

        car.moveBackwards();

        Assertions.assertEquals(1, car.getLocation(), "Car moved from position 1 when moveBackwards" +
                " was called.");

        for (int i = 0; i < 500; i++) {
            car.moveForward();
        }

        for (int i = 0; i < 500; i++) {
            car.moveBackwards();
        }

        // After moving all the way to the end of the street and back again, we assume that all parking spaces have
        // been discovered, and that the car is back at the first meter.
        Assertions.assertEquals(1, car.getLocation(), "Car did not return to the beginning.");
        Assertions.assertEquals(10, car.getEmpty_parking_places(), "Car did not find and retain " +
                "all parking spaces.");

        // Move back and forth the entire street once more to see if the parking spaces are added again.
        for (int i = 0; i < 500; i++) {
            car.moveForward();
        }

        for (int i = 0; i < 500; i++) {
            car.moveBackwards();
        }

        Assertions.assertEquals(10, car.getEmpty_parking_places(), "Car registered parking spaces" +
                " twice.");
    }
}
