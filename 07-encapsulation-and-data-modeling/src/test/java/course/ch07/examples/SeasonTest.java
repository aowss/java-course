package course.ch07.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeasonTest {

    @Test
    void displayNameReturnsLabel() {
        assertEquals("Summer", Season.SUMMER.displayName());
    }

    @Test
    void averageTempFahrenheit() {
        assertEquals(82.4, Season.SUMMER.averageTempFahrenheit(), 0.1);
    }

    @Test
    void nextCyclesThroughSeasons() {
        assertEquals(Season.SUMMER, Season.SPRING.next());
        assertEquals(Season.AUTUMN, Season.SUMMER.next());
        assertEquals(Season.WINTER, Season.AUTUMN.next());
        assertEquals(Season.SPRING, Season.WINTER.next());
    }

    @Test
    void isWarmerThan() {
        assertTrue(Season.SUMMER.isWarmerThan(20));
        assertFalse(Season.WINTER.isWarmerThan(5));
    }

    @Test
    void valuesReturnsAllSeasons() {
        assertEquals(4, Season.values().length);
    }
}
