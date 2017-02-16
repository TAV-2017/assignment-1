package car_components;


public class ActuatorImpl implements Actuator {

    private int location;

    @Override
    public int forward() {
        if (location < (STREET_SIZE - 1)) {
            location += 1;
        }
        return location;
    }

    @Override
    public int backward() {
        if (location > 0) {
            location -= 1;
        }
        return location;
    }

    public int getLocation() {
        return location;
    }
}
