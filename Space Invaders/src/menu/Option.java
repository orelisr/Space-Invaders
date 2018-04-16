package menu;

/**
 * @version 4.0
 * @since
 * @param <T>
 *            The return type of the method run.
 */
public class Option<T> {
    private String key1;
    private String message;
    private T      returnVal;

    /**
     * This method is the constructor of the object, defying each member of
     * option of the menu.
     *
     * @param key2
     *            is the key pressed when choosing the current option.
     * @param mes
     *            is the message of the option chosen.
     * @param retVal
     *            is the task to be returned as choosing option.
     */
    public Option(String key2, String mes, T retVal) {
        this.key1 = key2;
        this.message = mes;
        this.returnVal = retVal;
    }

    /**
     * This method returns the option's key.
     *
     * @return the option key.
     */
    public String getKey() {
        return this.key1;
    }

    /**
     * This method returns the option's message when chosen.
     *
     * @return the options message as a string.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * This method returns the task that should be taken when choosing an
     * option.
     *
     * @return the returnVal of the option.
     */
    public T getReturnVal() {
        return this.returnVal;
    }
}