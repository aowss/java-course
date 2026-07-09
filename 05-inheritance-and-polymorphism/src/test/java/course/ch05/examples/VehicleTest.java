package course.ch05.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {

    @Test
    void vehicleDescribe() {
        var v = new Vehicle("Generic");
        assertEquals("Generic vehicle", v.describe());
    }

    @Test
    void carOverridesDescribe() {
        var c = new Car("Toyota", 4);
        assertEquals("Toyota car with 4 doors", c.describe());
    }

    @Test
    void motorcycleOverridesDescribe() {
        var m = new Motorcycle("Harley-Davidson");
        assertEquals("Harley-Davidson motorcycle", m.describe());
    }

    @Test
    void dynamicDispatchThroughBaseReference() {
        Vehicle v = new Car("Honda", 2);
        assertEquals("Honda car with 2 doors", v.describe());
    }
}
