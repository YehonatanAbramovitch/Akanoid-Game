/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package GameUpdating;

import GameMain.Game;
import MovingAndMechanics.Counter;
import Objects.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCount;

    /**
     * Constructor for the ScoreIndicator class.
     *
     * @param scoreCount the counter representing the player's score
     */
    public ScoreIndicator(Counter scoreCount) {
        this.scoreCount = scoreCount;
    }

    /**
     * Gets the player's score counter.
     *
     * @return the player's score counter
     */
    public Counter getScore() {
        return scoreCount;
    }

    /**
     * Sets the player's score counter.
     *
     * @param score the player's score counter
     */
    public void setScore(Counter score) {
        this.scoreCount = score;
    }

    /**
     * Adds the score indicator to the given game.
     *
     * @param g the game to add the score indicator to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Displays the current score as a string.
     *
     * @return a string representation of the current score
     */
    public String displayScore() {
        return "Score:" + this.scoreCount.getValue();
    }

    /**
     * Draws the score indicator on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 15);
        d.setColor(Color.black);
        d.drawText(350, 15, this.displayScore(), 20);
    }

    /**
     * This method is called when time has passed in the game.
     */
    public void timePassed() {

    }
}
