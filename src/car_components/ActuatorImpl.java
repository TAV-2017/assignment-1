package car_components;


public class ActuatorImpl implements Actuator {
    private int location;

    @Override
    public int forward() {
        return 0;
    }

    @Override
    public int backward() {
        return 0;
    }

    public int getLocation() {
        return location;
    }
}
