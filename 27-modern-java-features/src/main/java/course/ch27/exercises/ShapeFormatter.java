package course.ch27.exercises;

/**
 * Exercise 1 (Guided): Format shapes using pattern matching switch.
 *
 * <p>Implement {@link #format(Shape)} to return a single-line description of each shape.
 * Use guarded patterns to detect squares and invalid dimensions.
 *
 * <pre>{@code
 * format(new Rectangle(2, 2)); // "square 2.0"
 * }</pre>
 */
public final class ShapeFormatter {

    private ShapeFormatter() {
    }

    /**
     * Sealed root type for geometric shapes.
     */
    public sealed interface Shape permits Circle, Rectangle, Triangle {
    }

    /**
     * A circle defined by its radius.
     *
     * @param radius the radius
     */
    public record Circle(double radius) implements Shape {
    }

    /**
     * A rectangle defined by width and height.
     *
     * @param width  the horizontal side length
     * @param height the vertical side length
     */
    public record Rectangle(double width, double height) implements Shape {
    }

    /**
     * A triangle defined by base and height.
     *
     * @param base   the length of the base
     * @param height the perpendicular height
     */
    public record Triangle(double base, double height) implements Shape {
    }

    /**
     * Formats a shape as a concise label.
     *
     * <p>Expected formats:
     * <ul>
     *   <li>Invalid circle: {@code "invalid circle"}</li>
     *   <li>Circle: {@code "circle <radius>"}</li>
     *   <li>Invalid rectangle: {@code "invalid rectangle"}</li>
     *   <li>Square: {@code "square <side>"}</li>
     *   <li>Rectangle: {@code "rectangle <width>x<height>"}</li>
     *   <li>Invalid triangle: {@code "invalid triangle"}</li>
     *   <li>Triangle: {@code "triangle <base>x<height>"}</li>
     * </ul>
     *
     * @param shape the shape to format
     * @return a formatted label
     */
    public static String format(Shape shape) {
        // TODO: use pattern matching switch with when guards
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
