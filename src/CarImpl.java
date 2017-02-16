import car_components.Actuator;
import car_components.ActuatorImpl;
import car_components.Sensor;
import car_components.SensorImpl;

public class CarImpl implements Car {

    SensorImpl sensor_1 = new SensorImpl();
    SensorImpl sensor_2 = new SensorImpl();

    ActuatorImpl actuator = new ActuatorImpl();

    private boolean[] visited = new boolean[Sensor.STREET_SIZE];
    private boolean[] empty = new boolean[Sensor.STREET_SIZE];

    private int size;
    private boolean parked;

    private static final int DEFAULT_CAR_SIZE = 5;

    public CarImpl() {
        this(DEFAULT_CAR_SIZE);
    }

    public CarImpl(int size) {
        this.size = size;
    }

    public void setSensor_1(SensorImpl sensor_1) {
        this.sensor_1 = sensor_1;
    }

    public void setSensor_2(SensorImpl sensor_2) {
        this.sensor_2 = sensor_2;
    }

    /**
     * Get car size
     */
    public int getSize() {
        return size;
    }

    @Override
    public int isEmpty() {
        int sensor_1_total = 0;
        int sensor_2_total = 0;

        // Get sensor values five times, as per the assignment specification.
        for (int i = 0; i < 5; i++) {
            sensor_1_total += sensor_1.measureDistance(actuator.getLocation());
            sensor_2_total += sensor_2.measureDistance(actuator.getLocation());
        }

        // Get the average values for the sensors, as per the assignment specification.
        int sensor_1_average = sensor_1_total / 5;
        int sensor_2_average = sensor_2_total / 5;

        boolean sensor_1_valid = Sensor.isValid(sensor_1_average);
        boolean sensor_2_valid = Sensor.isValid(sensor_2_average);

        if (sensor_1_valid && sensor_2_valid) {
            return (sensor_1_average + sensor_2_average) / 2;
        }

        if (sensor_1_valid) {
            return sensor_1_average;
        }

        if (sensor_2_valid) {
            return sensor_2_average;
        }

        throw new IllegalStateException();
    }

    private void recordSensor() {
        int location = actuator.getLocation();

        visited[location] = true;
        empty[location] = isEmpty() == 200;
    }

    /**
     * Get whether the car is at the end of an empty place
     */
    public boolean isAtEmptyPlace() {
        int location = actuator.getLocation();

        boolean isEmptyPlace = true;
        for (int i = 0; i < size; i++) {
            isEmptyPlace &= (location - i >= 0) && visited[location - i] && empty[location - i];
        }

        return isEmptyPlace;
    }

    /**
     * Count the number of empty places found in the map
     */
    private int countEmptyPlaces() {
        int places = 0;

        int tempEmptyLocations = 0;

        for (int i = 0; i < Sensor.STREET_SIZE; i++) {
            if (empty[i]) {
                tempEmptyLocations += 1;
            } else {
                tempEmptyLocations = 0;
            }

            if (tempEmptyLocations == size) {
                places += 1;
                tempEmptyLocations = 0;
            }

            if (!visited[i]) {
                break;
            }
        }

        return places;
    }

    @Override
    public int[] moveForward() {
        recordSensor();
        actuator.forward();
        return new int[] { actuator.getLocation(), countEmptyPlaces() };
    }

    @Override
    public int[] moveBackward() {
        recordSensor();
        actuator.backward();
        return new int[] { actuator.getLocation(), countEmptyPlaces() };
    }

    @Override
    public void park() {
        if (parked) {
            throw new IllegalStateException();
        }

        recordSensor();

        while (!isAtEmptyPlace()) {
            actuator.forward();
            recordSensor();

            if (actuator.getLocation() == (Actuator.STREET_SIZE - 1)) {
                break;
            }
        }

        if (isAtEmptyPlace()) {
            // Right now the car is overlapping with the empty place.
            // Move one step forward for the empty place to end right where the car begins.
            actuator.forward();

            parked = true;

            for (int i = 0; i < size; i++) {
                actuator.backward();
            }
        } else {
            parked = false;
        }
    }

    @Override
    public void unPark() {
        if (parked) {
            parked = false;

            for (int i = 0; i < size; i++) {
                actuator.forward();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public int[] whereIs() {
        return new int[] { actuator.getLocation(), parked ? 1 : 0 };
    }
}
