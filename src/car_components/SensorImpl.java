package car_components;


public class SensorImpl implements Sensor{
    private boolean street[];

    private static final int STREET_SIZE = 500;

    public SensorImpl() {
        street = new boolean[STREET_SIZE];

        // Street layout set according to assignment specification, two available parking spaces.
        for (int i = 0; i < 5; i++) {
            street[i + 100] = true;
        }

        // The second parking space has a different size.
        for (int i = 0; i < 7; i++) {
            street[i + 200] = true;
        }

        // The third space is too small to park in, as per assignment instructions.
        for (int i = 0; i < 3; i++) {
            street[i + 300] = true;
        }
    }

    @Override
    public int measureDistance(int location) {
        return street[location - 1] ? 200 : 0;
    }
}
