/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package GameUpdating;

import GameMain.Game;
import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
import Objects.Ball;
import Objects.Block;

/**
 * BallRemover class.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor for the BallRemover class.
     *
     * @param game           the Game object
     * @param remainingBalls the counter representing the remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever a block is hit by a ball.
     * It removes the hitting ball from the game and decreases the count of remaining balls.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}