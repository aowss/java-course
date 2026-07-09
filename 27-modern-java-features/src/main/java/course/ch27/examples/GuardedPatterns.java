package course.ch27.examples;

/**
 * Demonstrates guarded patterns with a sealed {@link Shape} hierarchy.
 *
 * <p>A {@code when} clause adds a boolean guard to a pattern case. Guards are evaluated
 * only after the pattern matches, enabling specialized cases such as recognizing squares
 * as a special kind of rectangle.
 *
 * <pre>{@code
 * String desc = GuardedPatterns.describe(new Rectangle(4, 4));
 * // "square side=4.0"
 * }</pre>
 */
public final class GuardedPatterns {

    private GuardedPatterns() {
    }

    /**
     * Sealed root type for geometric shapes.
     */
    public sealed interface Shape permits Circle, Rectangle, Triangle {
    }

    /**
     * A circle defined by its radius.
     *
     * @param radius the radius (must be positive for a valid shape)
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
     * Returns a descriptive label for the given shape.
     *
     * @param shape a shape instance
     * @return a formatted description
     */
    public static String describe(Shape shape) {
        return switch (shape) {
            case Circle c when c.radius() <= 0 -> "invalid circle";
            case Circle c -> "circle radius=" + c.radius();
            case Rectangle r when r.width() <= 0 || r.height() <= 0 -> "invalid rectangle";
            case Rectangle r when r.width() == r.height() -> "square side=" + r.width();
            case Rectangle r -> "rectangle " + r.width() + "x" + r.height();
            case Triangle t when t.base() <= 0 || t.height() <= 0 -> "invalid triangle";
            case Triangle t -> "triangle base=" + t.base() + " height=" + t.height();
        };
    }

    /**
     * Computes the area of a shape using exhaustive pattern matching.
     *
     * @param shape a valid (non-degenerate) shape
     * @return the area
     */
    public static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            case Triangle t -> 0.5 * t.base() * t.height();
        };
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(3),
                new Rectangle(4, 4),
                new Rectangle(3, 5),
                new Triangle(6, 4),
                new Circle(0)
        };
        for (Shape shape : shapes) {
            System.out.println(describe(shape) + " area=" + area(shape));
        }
    }
}
