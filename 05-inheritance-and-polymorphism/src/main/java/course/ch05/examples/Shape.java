package course.ch05.examples;

/**
 * Abstract base class for a shape hierarchy.
 *
 * <p>Demonstrates abstract classes and how subclasses provide
 * concrete implementations of {@link #area()} and {@link #perimeter()}.
 */
public abstract class Shape {

    /**
     * Returns the area of this shape.
     *
     * @return the area
     */
    public abstract double area();

    /**
     * Returns the perimeter of this shape.
     *
     * @return the perimeter
     */
    public abstract double perimeter();

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(5.0), new Rectangle(4.0, 6.0)};
        for (var shape : shapes) {
            System.out.printf("%s → area=%.2f, perimeter=%.2f%n",
                    shape, shape.area(), shape.perimeter());
        }
    }
}
