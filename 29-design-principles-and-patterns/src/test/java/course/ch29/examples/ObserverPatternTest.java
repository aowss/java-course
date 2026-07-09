package course.ch29.examples;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObserverPatternTest {

    @Test
    void observersReceiveUpdates() {
        ObserverPattern.WeatherStation station = new ObserverPattern.WeatherStation();
        List<Double> readings = new ArrayList<>();
        station.addObserver(readings::add);
        station.setTemperature(20.0);
        station.setTemperature(25.0);
        assertEquals(List.of(20.0, 25.0), readings);
    }

    @Test
    void getTemperatureReturnsCurrentValue() {
        ObserverPattern.WeatherStation station = new ObserverPattern.WeatherStation();
        station.setTemperature(18.5);
        assertEquals(18.5, station.getTemperature());
    }

    @Test
    void removeObserverStopsNotifications() {
        ObserverPattern.WeatherStation station = new ObserverPattern.WeatherStation();
        List<Double> readings = new ArrayList<>();
        ObserverPattern.TemperatureObserver observer = readings::add;
        station.addObserver(observer);
        assertTrue(station.removeObserver(observer));
        station.setTemperature(30.0);
        assertEquals(List.of(), readings);
    }

    @Test
    void observerCountTracksRegistrations() {
        ObserverPattern.WeatherStation station = new ObserverPattern.WeatherStation();
        assertEquals(0, station.observerCount());
        station.addObserver(temp -> {});
        station.addObserver(temp -> {});
        assertEquals(2, station.observerCount());
    }

    @Test
    void removeNonexistentObserverReturnsFalse() {
        ObserverPattern.WeatherStation station = new ObserverPattern.WeatherStation();
        assertFalse(station.removeObserver(temp -> {}));
    }
}
