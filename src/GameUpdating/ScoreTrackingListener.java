/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package GameUpdating;

import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
import Objects.Ball;
import Objects.Block;

/**
 * ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor for the ScoreTrackingListener class.
     *
     * @param scoreCounter the counter representing the player's score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever a block is hit by a ball. It increases the player's score by 5 points.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}