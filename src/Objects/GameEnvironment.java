/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private List<Collidable> list = null;

    /**
     * Constructs a new GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a collidable to the environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * Assumes an object moving along the given trajectory. If this object will not collide with any of the collidables
     * in this collection,returns null.Otherwise,returns information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the object
     * @return information about the closest collision, or null if no collision will occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (list.isEmpty()) {
            return null;
        }
        Point minP = null;
        Collidable minC = null;
        for (Collidable collidable : list) {
            Rectangle r = collidable.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(r) != null) {
                if (minP == null) {
                    minP = trajectory.closestIntersectionToStartOfLine(r);
                    minC = collidable;
                } else {
                    if (trajectory.start().distance(minP) > (trajectory.start().
                            distance(trajectory.closestIntersectionToStartOfLine(r)))) {
                        minP = trajectory.closestIntersectionToStartOfLine(r);
                        minC = collidable;
                    }
                }
            }
        }
        if (minP == null) {
            return null;
        }
        return new CollisionInfo(minP, minC);
    }

    /**
     * Removes a given collidable from game objects.
     *
     * @param c the object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }
}
