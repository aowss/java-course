package course.ch21.examples;

/**
 * Converts between Celsius and Fahrenheit temperature scales.
 *
 * <p>Used to demonstrate parameterized tests with multiple input/output pairs.
 */
public class TemperatureConverter {

    /**
     * Converts Celsius to Fahrenheit.
     *
     * @param celsius the temperature in Celsius
     * @return the temperature in Fahrenheit
     */
    public static double toFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    /**
     * Converts Fahrenheit to Celsius.
     *
     * @param fahrenheit the temperature in Fahrenheit
     * @return the temperature in Celsius
     */
    public static double toCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}
