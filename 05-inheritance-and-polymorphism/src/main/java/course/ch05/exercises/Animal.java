package course.ch05.exercises;

/**
 * Exercise 1 (Guided): Base class for an animal hierarchy.
 *
 * <p>Implement {@link #speak()} to return {@code "..."} and
 * {@link #toString()} to return {@code "Animal{name='...'}"}. Subclasses
 * {@link Dog} and {@link Cat} override {@code speak()} with their own sounds.
 */
public class Animal {

    private String name;

    /**
     * Creates an animal with the given name.
     *
     * @param name the animal's name
     */
    public Animal(String name) {
        this.name = name;
    }

    /**
     * Returns the animal's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the sound this animal makes.
     *
     * @return a generic sound string {@code "..."}
     */
    public String speak() {
        // TODO: return "..."
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        // TODO: return "Animal{name='...'}"
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
