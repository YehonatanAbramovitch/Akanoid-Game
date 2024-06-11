/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package Objects;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * SpriteCollection class.
 */
public class SpriteCollection {
    private ArrayList<Sprite> list;

    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.list = new ArrayList<Sprite>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList1 = new ArrayList<Sprite>(this.list);
        for (Sprite sprite : spriteList1) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given draw surface.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : list) {
            sprite.drawOn(d);
        }
    }

    /**
     * Removes a given sprite from game objects.
     *
     * @param s the object to remove.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }
}