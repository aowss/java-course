package course.ch02.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureConverterTest {

    private static final double DELTA = 0.001;

    @Test
    void freezingPointCToF() {
        assertEquals(32.0, TemperatureConverter.celsiusToFahrenheit(0), DELTA);
    }

    @Test
    void boilingPointCToF() {
        assertEquals(212.0, TemperatureConverter.celsiusToFahrenheit(100), DELTA);
    }

    @Test
    void bodyTemperatureCToF() {
        assertEquals(98.6, TemperatureConverter.celsiusToFahrenheit(37), DELTA);
    }

    @Test
    void freezingPointFToC() {
        assertEquals(0.0, TemperatureConverter.fahrenheitToCelsius(32), DELTA);
    }

    @Test
    void boilingPointFToC() {
        assertEquals(100.0, TemperatureConverter.fahrenheitToCelsius(212), DELTA);
    }

    @Test
    void negativeTemperature() {
        assertEquals(-40.0, TemperatureConverter.celsiusToFahrenheit(-40), DELTA);
        assertEquals(-40.0, TemperatureConverter.fahrenheitToCelsius(-40), DELTA);
    }
}
