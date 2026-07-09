package course.ch05.exercises;

/**
 * Exercise 1 (Guided): A dog that barks.
 *
 * <p>Override {@link Animal#speak()} to return {@code "Woof!"}.
 */
public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        // TODO: return "Woof!"
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        // TODO: return "Dog{name='...'}"
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
