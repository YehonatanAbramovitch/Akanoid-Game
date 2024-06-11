/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import GameMain.Game;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;

/**
 * Ball class.
 */

public class Ball implements Sprite {
    private int r;
    private Point center;
    private java.awt.Color color;
    private GameEnvironment game;
    private static double epsilon = (1.0 / Math.pow(10, 5));
    private Velocity velocity = new Velocity(-5, -5);


    /**
     * Constructor for creating a ball with specified parameters.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param game   the game environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.game = game;
    }

    /**
     * Returns the x-coordinate of the center point.
     *
     * @return the x-coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the center point.
     *
     * @return the y-coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the game environment of the ball.
     *
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.game;
    }

    /**
     * change the radius of the ball.
     *
     * @param x the new radius size of the ball
     */
    public void setR(int x) {
        this.r = x;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * change the radius of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }


    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    /**
     * Adjusts the ball's radius if its current radius is larger than a quarter of the minimum
     * distance between the specified points (representing a rectangular boundary).
     *
     * @param x1 the x-coordinate of the first corner point of the boundary
     * @param y1 the y-coordinate of the first corner point of the boundary
     * @param x2 the x-coordinate of the second corner point of the boundary
     * @param y2 the y-coordinate of the second corner point of the boundary
     */
    public void radiusFix(double x1, double y1, double x2, double y2) {
        double d1 = x2 - x1;
        double d2 = y2 - y1;
        double x = this.center.getX();
        double y = this.center.getY();
        if ((y >= y1) && (y <= y2) && (x >= x1) && (x <= x2)) {
            if (this.getSize() > (Math.min(d1, d2) / 4)) {
                this.setR((int) (Math.min(d1, d2) / 4));
            }
        }
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the Velocity object representing the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy values.
     *
     * @param dx the change in x-coordinate per step
     * @param dy the change in y-coordinate per step
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step according to its velocity and handles collisions.
     * If the ball collides with an object, it adjusts its position and velocity accordingly.
     * If no collision occurs, the ball is repositioned according to the fixBall method.
     */
    public void moveOneStep() {
        Point point = new Point(this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        Line line = new Line(this.center, point);
        if (game.getClosestCollision(line) != null) {
            Point colllsionP = game.getClosestCollision(line).collisionPoint();
            Collidable collsionC = game.getClosestCollision(line).collisionObject();
            this.center = new Point(colllsionP.getX() - Math.signum(this.velocity.getDx()) * epsilon,
                    colllsionP.getY() - Math.signum(this.velocity.getDy()) * epsilon);
            this.velocity = collsionC.hit(this, colllsionP, this.velocity);
        } else {
            this.center = line.end();
        }
    }

    /**
     * Adjusts the ball's position and velocity when it hits the boundaries.
     *
     * @param maxX the maximum x-coordinate of the boundary
     * @param maxY the maximum y-coordinate of the boundary
     * @param minX the minimum x-coordinate of the boundary
     * @param minY the minimum y-coordinate of the boundary
     * @return a Point representing the adjusted position of the ball
     */
    public Point fixBall(int maxX, int maxY, int minX, int minY) {
        Point p = new Point(this.getVelocity().applyToPoint(this.center).getX(),
                this.getVelocity().applyToPoint(this.center).getY());
        if (this.r > p.getX() - minX) {
            p = new Point(minX + r, p.getY());
        }
        if (this.r > maxX - p.getX()) {
            p = new Point(maxX - r, p.getY());
        }
        if (this.r > p.getY() - minY) {
            p = new Point(p.getX(), minY + r);
        }
        if (this.r > maxY - p.getY()) {
            p = new Point(p.getX(), maxY - r);
        }
        return p;
    }

    /**
     * Notifies the ball that time has passed, causing it to move one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the given game as a sprite.
     *
     * @param game the game to add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Remove the ball from the given game.
     *
     * @param game the game to remove the ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

}

