import car_components.Actuator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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
     *  - Make sensor 1 FAIL
     *  - Keep moving along the street and park the car again (the car should be parked)
     *  - Un-park the car
     *  - Keep moving along the street until the current empty stretch finishes
     *  - Try to park the car again and fail
     */
    @Test
    public void testScenario1() {
        CarImpl car = new CarImpl();

        int[] whereIs = car.whereIs();
        Assertions.assertEquals(0, whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        int emptyPlaces = 0;
        for (int i = 0; i < Actuator.STREET_SIZE; i++) {
            emptyPlaces = car.moveForward()[1];
        }

        Assertions.assertEquals(2, emptyPlaces);

        whereIs = car.whereIs();
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

        // Make sensor 1 fail
        SensorImpl sensor_1 = Mockito.mock(SensorImpl.class);
        Mockito.when(sensor_1.measureDistance(Mockito.anyInt())).thenReturn(-1);
        car.setSensor_1(sensor_1);

        // Second space
        car.park();
        whereIs = car.whereIs();
        Assertions.assertEquals(200, whereIs[0]);
        Assertions.assertEquals(1, whereIs[1]);

        car.unPark();
        whereIs = car.whereIs();
        Assertions.assertEquals(200 + car.getSize(), whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        // Keep moving, try to park and fail
        while (car.isAtEmptyPlace()) {
            car.moveForward();
        }
        car.park();
        Assertions.assertEquals(0, car.whereIs()[1]);
    }

    /*
     * Scenario 2:
     *  - Start at the beginning of the street
     *  - Move along the street and park in the first available space
     *  - Try to park while parked and fail
     *  - Un-park
     *  - Try to un-park while not parked and fail
     *  - Make sensor 2 FAIL
     *  - Park in the next available space
     */
    @Test
    public void testScenario2() {
        CarImpl car = new CarImpl();

        int[] whereIs = car.whereIs();
        Assertions.assertEquals(0, whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        car.park();
        whereIs = car.whereIs();
        Assertions.assertEquals(100, whereIs[0]);
        Assertions.assertEquals(1, whereIs[1]);

        Assertions.assertThrows(IllegalStateException.class, () -> car.park());

        car.unPark();
        whereIs = car.whereIs();
        Assertions.assertEquals(100 + car.getSize(), whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        Assertions.assertThrows(IllegalStateException.class, () -> car.unPark());

        // Make sensor 2 fail
        SensorImpl sensor_2 = Mockito.mock(SensorImpl.class);
        Mockito.when(sensor_2.measureDistance(Mockito.anyInt())).thenReturn(-2);
        car.setSensor_2(sensor_2);

        car.park();
        whereIs = car.whereIs();
        Assertions.assertEquals(200, whereIs[0]);
        Assertions.assertEquals(1, whereIs[1]);
    }

    /*
     * Scenario 3:
     *  - Start at the beginning of the street
     *  - Make both sensors FAIL
     *  - Try to park and fail
     */
    @Test
    public void testScenario3() {
        CarImpl car = new CarImpl();

        int[] whereIs = car.whereIs();
        Assertions.assertEquals(0, whereIs[0]);
        Assertions.assertEquals(0, whereIs[1]);

        // Make sensor 1 fail
        SensorImpl sensor_1 = Mockito.mock(SensorImpl.class);
        Mockito.when(sensor_1.measureDistance(Mockito.anyInt())).thenReturn(-1);
        car.setSensor_1(sensor_1);

        // Make sensor 2 fail
        SensorImpl sensor_2 = Mockito.mock(SensorImpl.class);
        Mockito.when(sensor_2.measureDistance(Mockito.anyInt())).thenReturn(-2);
        car.setSensor_2(sensor_2);

        Assertions.assertThrows(IllegalStateException.class, () -> car.park());
    }
}
