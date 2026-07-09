package course.ch07.examples;

/**
 * Demonstrates enums with fields, constructors, and instance methods.
 */
public enum Season {

    SPRING("Spring", 15.0),
    SUMMER("Summer", 28.0),
    AUTUMN("Autumn", 12.0),
    WINTER("Winter", 2.0);

    private final String displayName;
    private final double averageTempCelsius;

    Season(String displayName, double averageTempCelsius) {
        this.displayName = displayName;
        this.averageTempCelsius = averageTempCelsius;
    }

    /**
     * @return the human-readable season name
     */
    public String displayName() {
        return displayName;
    }

    /**
     * @return the average temperature in Celsius
     */
    public double averageTempCelsius() {
        return averageTempCelsius;
    }

    /**
     * Converts the average temperature to Fahrenheit.
     *
     * @return the average temperature in Fahrenheit
     */
    public double averageTempFahrenheit() {
        return averageTempCelsius * 9.0 / 5.0 + 32;
    }

    /**
     * Returns the season that follows this one in the calendar year.
     *
     * @return the next season
     */
    public Season next() {
        Season[] values = values();
        return values[(ordinal() + 1) % values.length];
    }

    /**
     * Returns whether this season's average temperature exceeds the given threshold.
     *
     * @param thresholdCelsius the threshold in Celsius
     * @return {@code true} if warmer than the threshold
     */
    public boolean isWarmerThan(double thresholdCelsius) {
        return averageTempCelsius > thresholdCelsius;
    }

    public static void main(String[] args) {
        for (Season season : values()) {
            System.out.printf("%s: %.1f°C (%.1f°F), next: %s%n",
                    season.displayName(),
                    season.averageTempCelsius(),
                    season.averageTempFahrenheit(),
                    season.next().displayName());
        }
    }
}
