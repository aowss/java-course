package course.ch09.examples;

/**
 * A generic pair holding two values of potentially different types.
 *
 * <p>Demonstrates generic classes with multiple type parameters.
 *
 * @param <K> the type of the first element
 * @param <V> the type of the second element
 */
public class Pair<K, V> {

    private final K first;
    private final V second;

    /**
     * Creates a pair with the given values.
     *
     * @param first  the first element
     * @param second the second element
     */
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Factory method for creating a pair.
     *
     * @param first  the first element
     * @param second the second element
     * @param <K>    the type of the first element
     * @param <V>    the type of the second element
     * @return a new pair
     */
    public static <K, V> Pair<K, V> of(K first, V second) {
        return new Pair<>(first, second);
    }

    /**
     * Returns the first element.
     *
     * @return the first element
     */
    public K getFirst() {
        return first;
    }

    /**
     * Returns the second element.
     *
     * @return the second element
     */
    public V getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static void main(String[] args) {
        Pair<String, Integer> nameAge = Pair.of("Alice", 30);
        System.out.println(nameAge);
        System.out.println("Name: " + nameAge.getFirst());
        System.out.println("Age:  " + nameAge.getSecond());

        Pair<String, String> coordinate = Pair.of("lat", "40.7128");
        System.out.println(coordinate);
    }
}
