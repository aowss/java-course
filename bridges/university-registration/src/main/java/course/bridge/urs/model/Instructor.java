package course.bridge.urs.model;

import java.util.Objects;

/**
 * A course instructor.
 */
public final class Instructor {

    private static int nextId = 1;

    private final int id;
    private final String name;

    public Instructor(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Instructor other && id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Instructor{id=" + id + ", name='" + name + "'}";
    }

    /** Resets auto-generated ids between tests. */
    public static void resetIdSequenceForTests() {
        nextId = 1;
    }
}
