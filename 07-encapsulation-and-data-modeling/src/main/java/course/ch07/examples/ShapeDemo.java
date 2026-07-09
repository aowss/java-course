package course.ch07.examples;

/**
 * Demonstrates sealed classes and pattern matching with {@code switch} and {@code instanceof}.
 */
public final class ShapeDemo {

    private ShapeDemo() {
    }

    /**
     * A sealed shape hierarchy — only permitted subclasses can extend {@link Shape}.
     */
    public sealed interface Shape permits Circle, Rectangle, Triangle {
        /**
         * @return the area of the shape
         */
        double area();
    }

    /**
     * @param radius the circle radius
     */
    public record Circle(double radius) implements Shape {
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    /**
     * @param width  the rectangle width
     * @param height the rectangle height
     */
    public record Rectangle(double width, double height) implements Shape {
        @Override
        public double area() {
            return width * height;
        }
    }

    /**
     * @param base   the triangle base
     * @param height the triangle height
     */
    public record Triangle(double base, double height) implements Shape {
        @Override
        public double area() {
            return 0.5 * base * height;
        }
    }

    /**
     * Describes a shape using pattern matching {@code switch}.
     *
     * @param shape the shape to describe
     * @return a human-readable description
     */
    public static String describe(Shape shape) {
        return switch (shape) {
            case Circle c -> "Circle with radius " + format(c.radius());
            case Rectangle r -> "Rectangle " + format(r.width()) + "×" + format(r.height());
            case Triangle t -> "Triangle base=" + format(t.base()) + " height=" + format(t.height());
        };
    }

    private static String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    /**
     * Returns the area, using pattern matching {@code instanceof}.
     *
     * @param shape the shape (may be any object)
     * @return the area, or {@code -1} if not a shape
     */
    public static double areaOf(Object shape) {
        if (shape instanceof Circle c) {
            return c.area();
        } else if (shape instanceof Rectangle r) {
            return r.area();
        } else if (shape instanceof Triangle t) {
            return t.area();
        }
        return -1;
    }

    /**
     * Sums the areas of all shapes in the array.
     *
     * @param shapes the shapes to sum
     * @return the total area
     */
    public static double totalArea(Shape... shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area();
        }
        return total;
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(3), new Rectangle(4, 5), new Triangle(6, 4)};
        for (Shape shape : shapes) {
            System.out.println(describe(shape) + " → area " + shape.area());
        }
        System.out.println("Total area: " + totalArea(shapes));
    }
}
