package course.ch22.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A concrete audit log class used to demonstrate spies.
 *
 * <p>A spy wraps a real object, allowing you to verify interactions
 * while still using the real implementation.
 */
public class AuditLog {

    private final List<String> entries = new ArrayList<>();

    /**
     * Records an audit event.
     *
     * @param event the event description
     */
    public void record(String event) {
        entries.add(event);
    }

    /**
     * @return the number of recorded events
     */
    public int size() {
        return entries.size();
    }

    /**
     * @return an unmodifiable view of all recorded events
     */
    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
