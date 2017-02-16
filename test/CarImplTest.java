import car_components.Actuator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import car_components.ActuatorImpl;
import car_components.SensorImpl;

public class CarImplTest {

    /*
     * Scenario 1:
     *  - Start at the beginning of the street
     *  - Move along the street and scan the available parking places
     *  - Move backward to the beginning of the street
     *  - Move along the street and park the car in the first available place (the car should be parked)
     *  - Un-park the car
     *  - Keep moving along the street and park the car again (the car should be parked)
     *  - Un-park the car
     *  - Keep moving along the street until the current empty stretch finishes
     *  - Try to park the car again and fail
     */
    @Test
    public void testScenario1() {
        CarImpl car = new CarImpl();

        Assertions.assertEquals(0, car.whereIs()[0]);

        int emptyPlaces = 0;
        for (int i = 0; i < Actuator.STREET_SIZE; i++) {
            emptyPlaces = car.moveForward()[1];
        }

        Assertions.assertEquals(2, emptyPlaces);

        int[] whereIs = car.whereIs();
        Assertions.assertEquals(Actuator.STREET_SIZE - 1, whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        for (int i = 0; i < Actuator.STREET_SIZE; i++) {
            car.moveBackward();
        }

        whereIs = car.whereIs();
        Assertions.assertEquals(0, whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        // First space
        car.park();
        whereIs = car.whereIs();
        Assertions.assertEquals(100, whereIs[0]);
        Assertions.assertEquals(1, whereIs[1]);

        car.unPark();
        whereIs = car.whereIs();
        Assertions.assertEquals(100 + car.getSize(), whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        // Second space
        car.park();
        whereIs = car.whereIs();
        Assertions.assertEquals(200, whereIs[0]);
        Assertions.assertEquals(1, whereIs[1]);

        car.unPark();
        whereIs = car.whereIs();
        Assertions.assertEquals(200 + car.getSize(), whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        // Keep moving and try to park
        while (car.isAtEmptyPlace()) {
            car.moveForward();
        }
        car.park();
        Assertions.assertEquals(0, car.whereIs()[1]);
    }
}
