import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CarTest {

    Car car;

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
    }

    @Test
    public void testIsEmpty() {
        car = new Car();

        boolean isInRange = car.isEmpty() >= 0 && car.isEmpty() <= 200 ? true : false;

        Assertions.assertEquals(true, isInRange, "Car sensors gave a value outside of 0 -> 200.");
    }

    @Test
    public void testMoveBackwards() {
        car = new Car();

        car.moveBackwards();

        Assertions.assertEquals(1, car.getLocation(), "Car moved from position 1 when movebackwards" +
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
        Assertions.assertEquals(10, car.getEmpty_parking_places(), "Car did not find and retain all parking " +
                "spaces.");

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

    @Test
    public void testParkCar(){
        car = new Car();

        car.park();

        if (car.getLocation() == 500) {
            Assertions.assertEquals(false, car.getParked(), "The car should not be parked at the" +
                    " very end of the street.");
        } else {
            Assertions.assertEquals(true, car.getParked(), "Park did not move the car to the very" +
                    " end of the street, which means it should have parked somewhere.");
        }
    }
}
