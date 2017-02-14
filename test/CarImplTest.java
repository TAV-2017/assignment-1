import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CarImplTest {

    CarImpl car;

    /**
     * TC1
     */

    @Test
    public void testMoveForward() {
        car = new CarImpl();

        Assertions.assertEquals(1, car.getLocation());
    }

    /**
     * TC2
     */

    @Test
    public void testMoveForward2() {
        car = new CarImpl();

        car.moveForward();

        Assertions.assertEquals(2, car.getLocation());
    }

    /**
     * TC3
     */

    @Test
    public void testMoveForward3() {
        car = new CarImpl();

        for (int i = 0; i < 501; i++) {
            car.moveForward();
        }

        Assertions.assertEquals(500, car.getLocation());
    }

    /**
     * TC4
     */

    @Test
    public void testMoveForward4() {
        car = new CarImpl();

        Assertions.assertEquals(0, car.getEmpty_parking_places());
    }

    /**
     * TC5
     */

    @Test
    public void testMoveForward5() {
        car = new CarImpl();

        int steps = 250;

        for (int i = 0; i < steps; i++) {
            car.moveForward();
        }

        int first_result[] = car.moveForward();

        for (int i = 0; i <= steps; i++) {
            car.moveBackward();
        }

        for (int i = 0; i < steps; i++) {
            car.moveForward();
        }

        int second_result[] = car.moveForward();

        Assertions.assertEquals(first_result[1], second_result[1]);
    }

    /**
     * TC6
     */

    @Test
    public void testMoveForward6() {
        car = new CarImpl();

        for (int i = 0; i < 100; i++) {
            car.moveForward();
        }

        int result[] = car.moveForward();

        Assertions.assertArrayEquals(new int[] {102, 2}, result);
    }

    /**
     * TC 7
     */

    @Test
    public void testIsEmpty() {
        car = new CarImpl();

        boolean range = car.isEmpty() >= 0 && car.isEmpty() <= 200;

        Assertions.assertEquals(true, range);
    }

    /**
     * TC 8
     */

    @Test
    public void testMoveBackwards() {
        car = new CarImpl();

        car.moveBackward();

        Assertions.assertEquals(1, car.getLocation());
    }

    /**
     * TC 9
     */

    @Test
    public void testMoveBackwards2() {
        car = new CarImpl();

        int steps = 10;

        for (int i = 0; i < steps; i++) {
            car.moveForward();
        }

        for (int i = 0; i < steps / 2; i++) {
            car.moveBackward();
        }

        Assertions.assertEquals(6, car.getLocation());
    }

    /**
     * TC 10
     */

    @Test
    public void testMoveBackwards3() {
        car = new CarImpl();

        int steps = 500;

        for (int i = 0; i < steps; i++) {
            car.moveForward();
        }

        for (int i = 0; i < steps; i++) {
            car.moveBackward();
        }

        Assertions.assertEquals(1, car.getLocation());

        Assertions.assertEquals(10, car.getEmpty_parking_places());
    }

    /**
     * TC 11
     */

    @Test
    public void testMoveBackwards4() {
        car = new CarImpl();

        int steps = 100;

        for (int i = 0; i < steps; i++) {
            car.moveForward();
        }

        for (int i = 0; i < steps / 2; i++) {
            car.moveBackward();
        }

        int ret[] = car.moveBackward();

        Assertions.assertArrayEquals(new int[] {50, 2}, ret);
    }

    /**
     * TC 12
     */

    @Test
    public void testPark1(){
        car = new CarImpl();

        car.park();

        Assertions.assertEquals(1, car.whereIs()[1]);
    }

    /**
     * TC 13
     */

    @Test
    public void testPark2() {
        car = new CarImpl();

        for (int i = 0; i < 500; i++) {
            car.moveForward();
        }

        car.park();

        Assertions.assertEquals(0, car.whereIs()[1]);
    }

    /**
     * TC 14
     */

    @Test
    public void testPark3() {
        car = new CarImpl();

        car.moveForward();
        car.moveBackward();
        car.park();

        Assertions.assertEquals(1, car.whereIs()[0]);
    }

    /**
     * TC 15
     */

    @Test
    public void testUnPark1() {
        car = new CarImpl();

        Assertions.assertThrows(IllegalStateException.class, () -> car.unPark());
    }

    /**
     * TC 16
     */

    @Test
    public void testUnPark2() {
        car = new CarImpl();

        car.park();
        car.unPark();

        Assertions.assertEquals(0, car.whereIs()[1]);
    }

    /**
     * TC 17
     */

    @Test
    public void testUnPark3() {
        car = new CarImpl();

        car.park();
        int parkedLocation = car.getLocation();

        car.unPark();
        int unParkedLocation = car.getLocation();

        Assertions.assertEquals(unParkedLocation, parkedLocation + 5);
    }

    /**
     * TC 18
     */

    @Test
    public void testWhereIs1() {
        car = new CarImpl();

        Assertions.assertArrayEquals(new int[] {1, 0}, car.whereIs());
    }

    /**
     * TC 19
     */

    @Test
    public void testWhereIs2() {
        car = new CarImpl();

        for (int i = 0; i < 500; i++) {
            car.moveForward();
        }

        Assertions.assertArrayEquals(new int[] {500, 0}, car.whereIs());
    }

    /**
     * TC 20
     */

    @Test
    public void testWhereIs3() {
        car = new CarImpl();

        car.park();

        Assertions.assertArrayEquals(new int[] {1, 1}, car.whereIs());
    }
}
