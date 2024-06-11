/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import biuoop.DrawSurface;

/**
 * Sprite interface.
 */
public interface Sprite {
    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the ball
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}