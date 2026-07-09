package course.ch07.examples;

/**
 * Demonstrates Java records — compact, immutable data carriers.
 *
 * <p>Records automatically generate constructors, accessors, {@code equals()},
 * {@code hashCode()}, and {@code toString()}.
 *
 * @param x the x-coordinate
 * @param y the y-coordinate
 */
public record Point(double x, double y) {

    /**
     * Returns the Euclidean distance from this point to the origin.
     *
     * @return the distance from (0, 0)
     */
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Returns the Euclidean distance to another point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distanceTo(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns a new point translated by the given offsets.
     *
     * @param dx the x offset
     * @param dy the y offset
     * @return the translated point
     */
    public Point translate(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }

    /**
     * Returns the midpoint between this point and another.
     *
     * @param other the other point
     * @return the midpoint
     */
    public static Point midpoint(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    public static void main(String[] args) {
        Point origin = new Point(0, 0);
        Point corner = new Point(3, 4);

        System.out.println("Origin: " + origin);
        System.out.println("Distance from origin: " + corner.distanceFromOrigin());
        System.out.println("Distance to origin:   " + corner.distanceTo(origin));
        System.out.println("Midpoint:             " + midpoint(origin, corner));
        System.out.println("Translated:           " + corner.translate(1, 1));
    }
}
