package course.ch05.examples;

/**
 * A car with a configurable number of doors.
 */
public class Car extends Vehicle {

    private final int doors;

    public Car(String make, int doors) {
        super(make);
        this.doors = doors;
    }

    @Override
    public String describe() {
        return getMake() + " car with " + doors + " doors";
    }

    public int getDoors() {
        return doors;
    }
}
