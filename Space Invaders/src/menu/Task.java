package menu;

/**
 * @version 4.0
 * @since
 * @param <T> The return type of the method run.
 */
public interface Task<T> {

    /**
     * This method does the task it suppose to do.
     *
     * @return A return value suitable to the task has been performed.
     */
    T run();
}