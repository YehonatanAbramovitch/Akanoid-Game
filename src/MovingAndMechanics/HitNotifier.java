/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package MovingAndMechanics;

/**
 * HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to the list of listeners to hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit listener from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}