public interface Car {

    /**
     Name: moveForward()
     Input: none
     Output: integer array: [0] - car’s location [1] - number of found parking spaces.
     Pre-condition: car object is instantiated
     Post-condition: car object’s location is now incremented by one

     Test cases:
     1.
    */

    int[] moveForward();

    /**
     Name: isEmpty()
     Input: none
     Output: integer in the range of 0 to 200
     Pre-condition: car object is instantiated
     Post-condition: car’s current status in terms of free space on the right is returned to calling instance.

     Test cases:
     1.
    */

    int isEmpty();

    /**
     Name: moveBackward()
     Input: none
     Output: integer array: [0] - car’s location [1] - number of found parking spaces.
     Pre-condition: car object is instantiated
     Post-condition: car object’s location is now decremented by one

     Test cases:
     1.
    */

    int[] moveBackward();

    /**
     Name: park()
     Input:
     Output:
     Pre-condition: car object is instantiated and is not parked
     Post-condition: the car is parked at a parking space if one was available. Otherwise, it should be located at the end of the street.

     Test cases:
     1.
    */

    boolean park();

    /**
     Name: unPark()
     Input:
     Output:
     Pre-condition: car object is instantiated
     Post-condition: the car should be located at the front of the parked at parking space, if the car was parked. Otherwise, nothing should happen.

     Test cases:
     1.
    */

    boolean unPark();

    /**
     Name: whereIs()
     Input:
     Output: integer array: [0] - the car’s position [1] - parked/not parked (1/0)
     Pre-condition: car object is instantiated
     Post-condition: the car’s position and parked status has been returned by the method.

     Test cases:
     1.
     */

    int[] whereIs();
}