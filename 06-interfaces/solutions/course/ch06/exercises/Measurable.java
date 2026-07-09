package course.ch06.exercises;

public final class Measurable {

    private Measurable() {
    }

    public interface MeasurableShape {
        double area();
        double perimeter();
    }

    public record Circle(double radius) implements MeasurableShape {

        public Circle {
            if (radius <= 0) {
                throw new IllegalArgumentException("radius must be positive");
            }
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }
    }

    public record Rectangle(double width, double height) implements MeasurableShape {

        public Rectangle {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("width and height must be positive");
            }
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public double perimeter() {
            return 2 * (width + height);
        }
    }

    public static double totalArea(MeasurableShape... shapes) {
        double total = 0;
        for (MeasurableShape shape : shapes) {
            total += shape.area();
        }
        return total;
    }

    public static void main(String[] args) {
        MeasurableShape[] shapes = {
                new Circle(3),
                new Rectangle(4, 5)
        };
        System.out.println("Total area: " + totalArea(shapes));
    }
}
