/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.Velocity;

/**
 * Collidable interface.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a collision with the collidable, returning the new velocity of an object
     * that collided with the Collidable.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @param hitter          the game's ball
     * @return the new velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


}