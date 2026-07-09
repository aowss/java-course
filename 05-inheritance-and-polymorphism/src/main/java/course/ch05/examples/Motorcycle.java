package course.ch05.examples;

/**
 * A motorcycle — a two-wheeled vehicle.
 */
public class Motorcycle extends Vehicle {

    public Motorcycle(String make) {
        super(make);
    }

    @Override
    public String describe() {
        return getMake() + " motorcycle";
    }
}
