package course.ch29.examples;

/**
 * Demonstrates the Strategy pattern: encapsulate interchangeable algorithms
 * behind a common interface.
 *
 * <p>Clients depend on the {@link PricingStrategy} abstraction, not concrete
 * pricing rules. New strategies can be added without modifying existing code
 * (Open/Closed Principle).
 *
 * <pre>{@code
 * double price = StrategyPattern.calculatePrice(100, new PercentageDiscount(0.10));
 * // 90.0
 * }</pre>
 */
public final class StrategyPattern {

    private StrategyPattern() {
    }

    /**
     * Strategy interface for computing a final price from a base amount.
     */
    @FunctionalInterface
    public interface PricingStrategy {
        /**
         * Applies the pricing rule to a base amount.
         *
         * @param basePrice the original price
         * @return the adjusted price
         */
        double apply(double basePrice);
    }

    /**
     * No discount — returns the base price unchanged.
     */
    public static final class NoDiscount implements PricingStrategy {
        @Override
        public double apply(double basePrice) {
            return basePrice;
        }
    }

    /**
     * Applies a percentage discount.
     *
     * @param percentage the discount fraction (0.10 = 10% off)
     */
    public record PercentageDiscount(double percentage) implements PricingStrategy {
        @Override
        public double apply(double basePrice) {
            return basePrice * (1 - percentage);
        }
    }

    /**
     * Applies a fixed amount discount, floored at zero.
     *
     * @param amount the fixed discount amount
     */
    public record FixedDiscount(double amount) implements PricingStrategy {
        @Override
        public double apply(double basePrice) {
            return Math.max(0, basePrice - amount);
        }
    }

    /**
     * Calculates the final price using the given strategy.
     *
     * @param basePrice the original price
     * @param strategy  the pricing strategy to apply
     * @return the final price
     */
    public static double calculatePrice(double basePrice, PricingStrategy strategy) {
        return strategy.apply(basePrice);
    }

    public static void main(String[] args) {
        double base = 100;
        System.out.println("No discount:  " + calculatePrice(base, new NoDiscount()));
        System.out.println("10% off:      " + calculatePrice(base, new PercentageDiscount(0.10)));
        System.out.println("$15 off:      " + calculatePrice(base, new FixedDiscount(15)));
    }
}
