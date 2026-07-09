package course.ch27.exercises;

public final class ShapeFormatter {

    private ShapeFormatter() {
    }

    public sealed interface Shape permits Circle, Rectangle, Triangle {
    }

    public record Circle(double radius) implements Shape {
    }

    public record Rectangle(double width, double height) implements Shape {
    }

    public record Triangle(double base, double height) implements Shape {
    }

    public static String format(Shape shape) {
        return switch (shape) {
            case Circle c when c.radius() <= 0 -> "invalid circle";
            case Circle c -> "circle " + c.radius();
            case Rectangle r when r.width() <= 0 || r.height() <= 0 -> "invalid rectangle";
            case Rectangle r when r.width() == r.height() -> "square " + r.width();
            case Rectangle r -> "rectangle " + r.width() + "x" + r.height();
            case Triangle t when t.base() <= 0 || t.height() <= 0 -> "invalid triangle";
            case Triangle t -> "triangle " + t.base() + "x" + t.height();
        };
    }
}
