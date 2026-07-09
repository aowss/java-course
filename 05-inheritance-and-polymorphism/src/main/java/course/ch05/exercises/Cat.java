package course.ch05.exercises;

/**
 * Exercise 1 (Guided): A cat that meows.
 *
 * <p>Override {@link Animal#speak()} to return {@code "Meow!"}.
 */
public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        // TODO: return "Meow!"
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        // TODO: return "Cat{name='...'}"
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
