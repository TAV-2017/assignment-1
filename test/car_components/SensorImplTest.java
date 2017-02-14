package car_components;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SensorImplTest {
    SensorImpl sensor;
    ActuatorImpl actuator;


    /**
     * TC1
     */

    @Test
    public void testReturnValRange() {
        sensor = new SensorImpl();
        actuator = new ActuatorImpl();

        boolean range = sensor.measureDistance(actuator.getLocation()) >= 0 &&
                sensor.measureDistance(actuator.getLocation()) <= 200;

        Assertions.assertEquals(true, range, "The measured range was out of bounds.");
    }
}
