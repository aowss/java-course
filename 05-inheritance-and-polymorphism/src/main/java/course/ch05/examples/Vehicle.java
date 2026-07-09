package course.ch05.examples;

/**
 * Base class for a vehicle hierarchy demonstrating dynamic dispatch.
 *
 * <p>The {@link #describe()} method is overridden by {@link Car} and
 * {@link Motorcycle} to show how the JVM picks the right method at runtime.
 */
public class Vehicle {

    private final String make;

    public Vehicle(String make) {
        this.make = make;
    }

    public String describe() {
        return make + " vehicle";
    }

    public String getMake() {
        return make;
    }

    @Override
    public String toString() {
        return describe();
    }

    public static void main(String[] args) {
        Vehicle[] fleet = {
                new Vehicle("Generic"),
                new Car("Toyota", 4),
                new Motorcycle("Harley-Davidson")
        };
        for (var v : fleet) {
            System.out.println(v.describe());
        }
    }
}
