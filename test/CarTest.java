import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CarTest {

    Car car;

    @Test
    public void testMoveForward() {
        car = new Car();

        Assertions.assertArrayEquals(new int[] {2, 0}, car.moveForward());

        for (int i = 0; i < 550; i++) {
            car.moveForward();
        }

        Assertions.assertEquals(500, car.getLocation(), "Location was incorrect.");
    }

    @Test
    public void testMoveForward_2() {
        car = new Car();

        for (int i = 0; i < 250; i++) {
            car.moveForward();
        }

        Assertions.assertArrayEquals(new int[] {252, 5}, car.moveForward(), "Incorrect number of parking " +
                "spaces or incorrect location.");
    }

    @Test
    public void testMoveBackwards() {
        car = new Car();

        for (int i = 50; i < 500; i++) {
            car.moveForward();
        }

        Assertions.assertArrayEquals(new int[] {452, 9}, car.moveForward());

        for (int i = 0; i < 500; i++) {
            car.moveBackwards();
        }

        Assertions.assertEquals(1, car.getLocation(), "Incorrect location.");

        Assertions.assertArrayEquals(new int[] {1, 0}, car.moveBackwards(), "Incorrect number of parking " +
                "spaces.");
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
