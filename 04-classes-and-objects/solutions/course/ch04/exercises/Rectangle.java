package course.ch04.exercises;

public class Rectangle {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle{width=" + width + ", height=" + height + "}";
    }
}
