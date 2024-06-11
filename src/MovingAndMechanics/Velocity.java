/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package MovingAndMechanics;

import GeometricPrimitives.Point;

/**
 * Velocity class.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor for creating a Velocity object with specified dx and dy.
     *
     * @param dx the change in x-coordinate per step
     * @param dy the change in y-coordinate per step
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a Velocity object from an angle and speed.
     *
     * @param angle the angle of the velocity vector
     * @param speed the speed of the velocity vector
     * @return a new Velocity object based on the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Returns the change in x-coordinate per step.
     *
     * @return the change in x-coordinate per step
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in y-coordinate per step.
     *
     * @return the change in y-coordinate per step
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Applies the velocity to a given point.
     *
     * @param p the point to which the velocity is applied
     * @return a new Point with the updated position after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}


