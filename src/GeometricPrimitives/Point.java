/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package GeometricPrimitives;

/**
 * Point class.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a point with given x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((other.getX() - this.getX()), 2) + Math.pow((other.getY() - this.getY()), 2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point to compare
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return ((other.getX() == this.getX()) && (other.getY() == this.getY()));
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}

