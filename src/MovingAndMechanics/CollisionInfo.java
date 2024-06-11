/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package MovingAndMechanics;

import GeometricPrimitives.Point;
import Objects.Collidable;

/**
 * CollisionInfo Class.
 */
public class CollisionInfo {
    private Point p;
    private Collidable c;

    /**
     * Constructs a new CollisionInfo with the specified collision point and collidable object.
     *
     * @param p the collision point
     * @param c the collidable object involved in the collision
     */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.c = c;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.p;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.c;
    }
}