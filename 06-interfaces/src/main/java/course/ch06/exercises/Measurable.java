package course.ch06.exercises;

/**
 * Exercise 1 (Guided): Measurable shapes.
 *
 * <p>Define a {@link Measurable} interface and implement {@link Circle} and
 * {@link Rectangle} with area and perimeter calculations.
 */
public final class Measurable {

    private Measurable() {
    }

    /**
     * A shape that exposes area and perimeter measurements.
     */
    public interface MeasurableShape {
        /**
         * @return the area of the shape
         */
        double area();

        /**
         * @return the perimeter of the shape
         */
        double perimeter();
    }

    /**
     * A circle defined by its radius.
     *
     * @param radius the radius (must be positive)
     */
    public record Circle(double radius) implements MeasurableShape {

        public Circle {
            if (radius <= 0) {
                throw new IllegalArgumentException("radius must be positive");
            }
        }

        @Override
        public double area() {
            // TODO: implement — Math.PI * radius * radius
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public double perimeter() {
            // TODO: implement — 2 * Math.PI * radius
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * A rectangle defined by width and height.
     *
     * @param width  the width (must be positive)
     * @param height the height (must be positive)
     */
    public record Rectangle(double width, double height) implements MeasurableShape {

        public Rectangle {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("width and height must be positive");
            }
        }

        @Override
        public double area() {
            // TODO: implement — width * height
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public double perimeter() {
            // TODO: implement — 2 * (width + height)
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * Sums the areas of the given shapes.
     *
     * @param shapes the shapes to measure
     * @return the total area
     */
    public static double totalArea(MeasurableShape... shapes) {
        // TODO: implement — sum each shape's area
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        MeasurableShape[] shapes = {
                new Circle(3),
                new Rectangle(4, 5)
        };
        System.out.println("Total area: " + totalArea(shapes));
    }
}
