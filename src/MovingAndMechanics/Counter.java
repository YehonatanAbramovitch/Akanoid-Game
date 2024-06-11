/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package MovingAndMechanics;

/**
 * Counter class.
 */
public class Counter {
    private int count;

    /**
     * Constructor for the Counter class. Initializes the counter with a starting value of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Adds the given number to the current count.
     *
     * @param number the number to add to the count
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtracts the given number from the current count.
     *
     * @param number the number to subtract from the count
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}