/**
 * Created by MTs on 29/01/17.
 *
 */

public class Caravan {
    private int position;
    private boolean parked;

    private int empty_meters;
    private int empty_parking_spaces;

    private boolean[] visited;

    private boolean[] street;

    private static final int STREET_SIZE = 500;


    public Caravan() {
        position = 1;
        parked = false;

        empty_meters = 0;
        empty_parking_spaces = 0;

        visited = new boolean[STREET_SIZE];

        /* To initialize a street to test with, that has a parking space every 50 meters. */
        street = new boolean[STREET_SIZE];

        for (int i = 0; i < 500; i += 50) {
            int counter = 0;
            for (int j = 0; j < 5; j++) {
                street[counter++] = true;
            }
        }
    }

    public void moveForward() {
        if (position < 500) {

            if (!visited[position - 1]) {
                visited[position - 1] = true;
            }

            position++;
            visited[position - 1] = true;
        }
    }

    public int getLocation() {
        return position;
    }
}
