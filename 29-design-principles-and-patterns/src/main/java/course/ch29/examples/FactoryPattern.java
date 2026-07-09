package course.ch29.examples;

/**
 * Demonstrates the Factory pattern: delegate object creation to a factory
 * method or class, hiding concrete types from clients.
 *
 * <p>Clients request objects by type identifier; the factory selects and
 * constructs the appropriate implementation.
 *
 * <pre>{@code
 * Vehicle car = FactoryPattern.create("car");
 * System.out.println(car.type()); // "car"
 * }</pre>
 */
public final class FactoryPattern {

    private FactoryPattern() {
    }

    /**
     * Common interface for vehicles created by the factory.
     */
    public interface Vehicle {
        /**
         * Returns the vehicle type identifier.
         *
         * @return the type name
         */
        String type();

        /**
         * Returns the maximum passenger capacity.
         *
         * @return passenger capacity
         */
        int capacity();
    }

    /**
     * A car with capacity 5.
     */
    public static final class Car implements Vehicle {
        @Override
        public String type() {
            return "car";
        }

        @Override
        public int capacity() {
            return 5;
        }
    }

    /**
     * A truck with capacity 2.
     */
    public static final class Truck implements Vehicle {
        @Override
        public String type() {
            return "truck";
        }

        @Override
        public int capacity() {
            return 2;
        }
    }

    /**
     * A bus with capacity 50.
     */
    public static final class Bus implements Vehicle {
        @Override
        public String type() {
            return "bus";
        }

        @Override
        public int capacity() {
            return 50;
        }
    }

    /**
     * Creates a vehicle by type name.
     *
     * @param type the vehicle type ("car", "truck", or "bus")
     * @return a new vehicle instance
     * @throws IllegalArgumentException if the type is unknown
     */
    public static Vehicle create(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "truck" -> new Truck();
            case "bus" -> new Bus();
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }

    public static void main(String[] args) {
        for (String type : new String[]{"car", "truck", "bus"}) {
            Vehicle vehicle = create(type);
            System.out.println(vehicle.type() + " capacity=" + vehicle.capacity());
        }
    }
}
