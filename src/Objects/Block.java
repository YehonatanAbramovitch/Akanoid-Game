/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import GameMain.Game;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.HitListener;
import MovingAndMechanics.HitNotifier;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private List<HitListener> hitListeners;


    /**
     * Constructs a Block with the given rectangle.
     *
     * @param rec the rectangle representing the block's position and size
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Returns the rectangle representing the block.
     *
     * @return the rectangle representing the block
     */
    public Rectangle getRec() {
        return this.rec;
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Handles a collision with the block, returning the new velocity of an object
     * that collided with the block.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @param hitter          the game's ball
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line line1 = new Line(rec.getUpperLeft(),
                new Point(rec.getUpperLeft().getX() + rec.getWidth(), rec.getUpperLeft().getY()));
        Line line2 = new Line(rec.getUpperLeft(),
                new Point(rec.getUpperLeft().getX(), rec.getUpperLeft().getY() + rec.getHeight()));
        Line line3 = new Line(new Point(rec.getUpperLeft().getX(), rec.getUpperLeft().getY() + rec.getHeight()),
                new Point((rec.getUpperLeft().getX() + rec.getWidth()), rec.getUpperLeft().getY() + rec.getHeight()));
        Line line4 = new Line(new Point(rec.getUpperLeft().getX() + rec.getWidth(), rec.getUpperLeft().getY()),
                new Point((rec.getUpperLeft().getX() + rec.getWidth()), rec.getUpperLeft().getY() + rec.getHeight()));

        if (line1.contain(collisionPoint)) {
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (line3.contain(collisionPoint)) {
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        if (line2.contain(collisionPoint)) {
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else if (line4.contain(collisionPoint)) {
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     * Notifies the block that time has passed.
     */
    public void timePassed() {
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.rec.getColor());
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * Adds the block to the given game as a sprite and a collidable.
     *
     * @param game the game to add the block to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Checks if the color of the given ball matches the color of the block.
     *
     * @param ball the ball to check its color
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.rec.getColor();
    }

    /**
     * Removes the block from the game. This involves removing it from the list of collidables and sprites in the game.
     *
     * @param game the game from which the block should be removed
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the block, allowing it to receive notifications about hits.
     *
     * @param hl the HitListener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the HitListener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
