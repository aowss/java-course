package course.ch29.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the Observer pattern: a subject notifies registered observers
 * when its state changes.
 *
 * <p>Observers register with the subject and receive updates without the subject
 * knowing their concrete types (Dependency Inversion Principle).
 *
 * <pre>{@code
 * WeatherStation station = new WeatherStation();
 * station.addObserver(temp -> System.out.println("Display: " + temp));
 * station.setTemperature(22.5);
 * }</pre>
 */
public final class ObserverPattern {

    private ObserverPattern() {
    }

    /**
     * Observer that reacts to temperature changes.
     */
    @FunctionalInterface
    public interface TemperatureObserver {
        /**
         * Called when the temperature changes.
         *
         * @param celsius the new temperature in Celsius
         */
        void onTemperatureChanged(double celsius);
    }

    /**
     * Subject that tracks temperature and notifies observers.
     */
    public static final class WeatherStation {

        private final List<TemperatureObserver> observers = new ArrayList<>();
        private double temperature;

        /**
         * Registers an observer.
         *
         * @param observer the observer to add
         */
        public void addObserver(TemperatureObserver observer) {
            observers.add(observer);
        }

        /**
         * Removes a previously registered observer.
         *
         * @param observer the observer to remove
         * @return {@code true} if the observer was removed
         */
        public boolean removeObserver(TemperatureObserver observer) {
            return observers.remove(observer);
        }

        /**
         * Returns the current temperature.
         *
         * @return temperature in Celsius
         */
        public double getTemperature() {
            return temperature;
        }

        /**
         * Updates the temperature and notifies all observers.
         *
         * @param celsius the new temperature
         */
        public void setTemperature(double celsius) {
            this.temperature = celsius;
            for (TemperatureObserver observer : observers) {
                observer.onTemperatureChanged(celsius);
            }
        }

        /**
         * Returns the number of registered observers.
         *
         * @return observer count
         */
        public int observerCount() {
            return observers.size();
        }
    }

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        station.addObserver(temp -> System.out.println("  Display: " + temp + "°C"));
        station.addObserver(temp -> System.out.println("  Logger: recorded " + temp));
        station.setTemperature(22.5);
        station.setTemperature(25.0);
    }
}
