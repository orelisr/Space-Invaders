package hithandlers;
/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class Counter {
    // Property.
    private int counter;

    /**
     * This method creates a new counter.
     * @param counterr (int) is the number that initialized
     * to be the first value of the counter.*/
    public Counter(int counterr) {
        this.counter = counterr;
    }

    /**
     * This method adds number to current count.
     * @param number is the number that is added to the counter.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
        }

    /**
     * This method subtracts number from current count.
     * @param number is the number that is reduced from the counter.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
        }

    /**
     * This method returns the current counter's value.
     * @return the current counter's value.
     */
    public int getValue() {
        return this.counter;
        }
}