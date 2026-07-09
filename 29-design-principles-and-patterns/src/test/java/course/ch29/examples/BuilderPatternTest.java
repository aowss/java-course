package course.ch29.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuilderPatternTest {

    @Test
    void buildWithDefaults() {
        BuilderPattern.Pizza pizza = BuilderPattern.pizza().build();
        assertEquals("medium", pizza.size());
        assertEquals("regular", pizza.crust());
        assertTrue(pizza.toppings().isEmpty());
    }

    @Test
    void buildWithCustomValues() {
        BuilderPattern.Pizza pizza = BuilderPattern.pizza()
                .size("large")
                .crust("thin")
                .topping("pepperoni")
                .topping("olive")
                .build();
        assertEquals("large", pizza.size());
        assertEquals("thin", pizza.crust());
        assertEquals(2, pizza.toppings().size());
    }

    @Test
    void toppingsListIsImmutable() {
        BuilderPattern.Pizza pizza = BuilderPattern.pizza().topping("cheese").build();
        assertEquals(1, pizza.toppings().size());
    }
}
