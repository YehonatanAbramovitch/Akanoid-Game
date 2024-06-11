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
 * BlockRemover class.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor for the BlockRemover class.
     *
     * @param game            the Game object
     * @param remainingBlocks the counter representing the remaining blocks in the game
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever a block is hit by a ball. It removes the hitting block from the game,
     * sets the hitter's color to the color of the hit block, and decreases the count of remaining blocks.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getRec().getColor());
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}