/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import GameMain.Game;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rec;

    /**
     * Constructs a Paddle with the given rectangle and keyboard.
     *
     * @param rec      the rectangle representing the paddle's position and size
     * @param keyboard the keyboard sensor for controlling the paddle
     */
    public Paddle(Rectangle rec, KeyboardSensor keyboard) {
        this.rec = rec;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left, handling collisions with the screen borders.
     */
    public void moveLeft() {
        if ((rec.getUpperLeft().getX() - 5) <= 20) {
            this.rec = new Rectangle(new Point(800, rec.getUpperLeft().getY()),
                    this.rec.getWidth(), this.rec.getHeight(), Color.black);
        } else {
            this.rec = new Rectangle(new Point(rec.getUpperLeft().getX() - 5, rec.getUpperLeft().getY()),
                    this.rec.getWidth(), this.rec.getHeight(), Color.black);
        }
    }

    /**
     * Moves the paddle to the right, handling collisions with the screen borders.
     */
    public void moveRight() {
        if ((rec.getUpperLeft().getX() + rec.getWidth()) >= 780) {
            this.rec = new Rectangle(new Point(0, rec.getUpperLeft().getY()),
                    this.rec.getWidth(), this.rec.getHeight(), Color.black);
        } else {
            this.rec = new Rectangle(new Point(rec.getUpperLeft().getX() + 5, rec.getUpperLeft().getY()),
                    this.rec.getWidth(), this.rec.getHeight(), Color.black);
        }
    }

    // Sprite

    /**
     * Notifies the paddle that time has passed.
     * Checks for keyboard input to move the paddle accordingly.
     */
    public void timePassed() {
        if (!(this.keyboard.isPressed(null))) {
            if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
            } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
            }
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    // Collidable

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Handles a collision with the paddle, returning the new velocity of an object
     * that collided with the paddle.
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
        double sectionWidth = this.rec.getWidth() / 5;
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));
        if (line1.contain(collisionPoint)) {
            if (collisionPoint.getX() - this.rec.getUpperLeft().getX() > 0
                    && collisionPoint.getX() - this.rec.getUpperLeft().getX() <= (sectionWidth)) {
                return Velocity.fromAngleAndSpeed(300, speed);
            } else if (collisionPoint.getX() - this.rec.getUpperLeft().getX() > (sectionWidth)
                    && collisionPoint.getX() - this.rec.getUpperLeft().getX() <= 2 * (sectionWidth)) {
                return Velocity.fromAngleAndSpeed(330, speed);
            } else if (collisionPoint.getX() - this.rec.getUpperLeft().getX() > 2 * (sectionWidth)
                    && collisionPoint.getX() - this.rec.getUpperLeft().getX() <= 3 * (sectionWidth)) {
                return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            } else if (collisionPoint.getX() - this.rec.getUpperLeft().getX() > 3 * (sectionWidth)
                    && collisionPoint.getX() - this.rec.getUpperLeft().getX() <= 4 * (sectionWidth)) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else if (collisionPoint.getX() - this.rec.getUpperLeft().getX() > 4 * (sectionWidth)
                    && collisionPoint.getX() - this.rec.getUpperLeft().getX() <= 5 * (sectionWidth)) {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        if (line2.contain(collisionPoint) || line4.contain(collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (line3.contain(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * Adds the paddle to the given game, making it a collidable and a sprite.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}