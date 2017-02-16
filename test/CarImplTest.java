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
     *  - Move backwards until the most efficient parking place
     *    (the smallest available parking where it can still park safely)
     *  - Park the car
     *  - Un-park the car and drive to the end of the street
     */
    @Test
    public void testScenario1() {
        CarImpl car = new CarImpl();

        Assertions.assertEquals(car.whereIs()[0], 0);
        for (int i = 0; i < Actuator.STREET_SIZE; i++) {
            car.moveForward();
        }
    }

}
