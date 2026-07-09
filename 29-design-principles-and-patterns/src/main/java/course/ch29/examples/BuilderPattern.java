package course.ch29.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the Builder pattern: construct complex objects step by step
 * with a fluent API.
 *
 * <p>The builder separates construction from representation, making object
 * creation readable and allowing optional fields without telescoping constructors.
 *
 * <pre>{@code
 * Pizza pizza = BuilderPattern.pizza()
 *     .size("large")
 *     .crust("thin")
 *     .topping("pepperoni")
 *     .build();
 * }</pre>
 */
public final class BuilderPattern {

    private BuilderPattern() {
    }

    /**
     * An immutable pizza with optional toppings.
     *
     * @param size     the pizza size
     * @param crust    the crust type
     * @param toppings the list of toppings
     */
    public record Pizza(String size, String crust, List<String> toppings) {
    }

    /**
     * Fluent builder for {@link Pizza}.
     */
    public static final class PizzaBuilder {

        private String size = "medium";
        private String crust = "regular";
        private final List<String> toppings = new ArrayList<>();

        /**
         * Sets the pizza size.
         *
         * @param size the size (e.g., "small", "medium", "large")
         * @return this builder
         */
        public PizzaBuilder size(String size) {
            this.size = size;
            return this;
        }

        /**
         * Sets the crust type.
         *
         * @param crust the crust (e.g., "thin", "thick")
         * @return this builder
         */
        public PizzaBuilder crust(String crust) {
            this.crust = crust;
            return this;
        }

        /**
         * Adds a topping.
         *
         * @param topping the topping name
         * @return this builder
         */
        public PizzaBuilder topping(String topping) {
            toppings.add(topping);
            return this;
        }

        /**
         * Builds the immutable {@link Pizza}.
         *
         * @return the constructed pizza
         */
        public Pizza build() {
            return new Pizza(size, crust, List.copyOf(toppings));
        }
    }

    /**
     * Creates a new {@link PizzaBuilder}.
     *
     * @return a fresh builder with defaults
     */
    public static PizzaBuilder pizza() {
        return new PizzaBuilder();
    }

    public static void main(String[] args) {
        Pizza pizza = pizza()
                .size("large")
                .crust("thin")
                .topping("pepperoni")
                .topping("mushroom")
                .build();
        System.out.println(pizza);
    }
}
