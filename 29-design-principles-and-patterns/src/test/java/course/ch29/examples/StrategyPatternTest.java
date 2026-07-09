package course.ch29.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyPatternTest {

    @Test
    void noDiscountReturnsBasePrice() {
        assertEquals(100, StrategyPattern.calculatePrice(100, new StrategyPattern.NoDiscount()));
    }

    @Test
    void percentageDiscount() {
        assertEquals(90, StrategyPattern.calculatePrice(100, new StrategyPattern.PercentageDiscount(0.10)));
    }

    @Test
    void fixedDiscount() {
        assertEquals(85, StrategyPattern.calculatePrice(100, new StrategyPattern.FixedDiscount(15)));
    }

    @Test
    void fixedDiscountFloorsAtZero() {
        assertEquals(0, StrategyPattern.calculatePrice(10, new StrategyPattern.FixedDiscount(15)));
    }
}
