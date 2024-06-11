/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package MovingAndMechanics;

import Objects.Ball;
import Objects.Block;

/**
 * HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
