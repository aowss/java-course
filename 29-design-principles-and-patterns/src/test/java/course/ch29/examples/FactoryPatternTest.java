package course.ch29.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactoryPatternTest {

    @Test
    void createCar() {
        FactoryPattern.Vehicle car = FactoryPattern.create("car");
        assertEquals("car", car.type());
        assertEquals(5, car.capacity());
    }

    @Test
    void createTruck() {
        FactoryPattern.Vehicle truck = FactoryPattern.create("truck");
        assertEquals("truck", truck.type());
        assertEquals(2, truck.capacity());
    }

    @Test
    void createBus() {
        FactoryPattern.Vehicle bus = FactoryPattern.create("bus");
        assertEquals("bus", bus.type());
        assertEquals(50, bus.capacity());
    }

    @Test
    void createIsCaseInsensitive() {
        assertEquals("car", FactoryPattern.create("CAR").type());
    }

    @Test
    void unknownTypeThrows() {
        assertThrows(IllegalArgumentException.class, () -> FactoryPattern.create("boat"));
    }
}
