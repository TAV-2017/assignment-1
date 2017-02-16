package car_components;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActuatorImplTest {
    ActuatorImpl actuator;

    /**
     * TC2
     */

    @Test
    public void testMoveForwardOnce() {
        actuator = new ActuatorImpl();

        int location = actuator.getLocation();

        Assertions.assertEquals(location + 1, actuator.forward(),
                "The car did not move the expected step.");
    }

    /**
     * TC3
     */

    @Test
    public void testMoveBeyondForwardStreetLimit() {
        actuator = new ActuatorImpl();

        for (int i = 0; i < 501; i++) {
            actuator.forward();
        }

        Assertions.assertEquals(500 - 1, actuator.getLocation(), "The car moved beyond the street limit.");
    }

    /**
     * TC4
     */

    @Test
    public void testMoveBackwardAfterMovingForward() {
        actuator = new ActuatorImpl();

        actuator.forward();
        actuator.forward();
        actuator.forward();
        actuator.forward();
        int location = actuator.forward();

        Assertions.assertEquals(5 - 1, actuator.backward(), "The car did not move the one expected step" +
                " backwards.");
    }

    /**
     * TC5
     */

    @Test
    public void testMoveBeyondBackwardStreetLimit() {
        actuator = new ActuatorImpl();

        Assertions.assertEquals(0, actuator.backward(), "The car moved beyond the street limit.");
    }
}
